package dmitriy;

import java.util.*;
import java.io.*;

public class MergeSorting {
public static void main(String...args) {
		
  MergeSorting ms = new MergeSorting(); 
  
  //вводимые параметры:
  try {
  String arg1 = args[0];//тип данных
  String arg2 = args[1];//выходной файл
  String arg3 = args[2];//входной файл
  String arg4 = args[3];//входной файл
  String arg5 = args[4];//входной файл
         
  if (arg1.equals("-i")&&arg2.equals("out.txt")&&arg3.equals("in1.txt")&&arg4.equals("in2.txt")&&arg5.equals("in3.txt"))
  ms.run();
  else
  System.out.println("Command line parameters entered incorrectly!.");  
  }
  catch(Exception e) {
  System.err.println("Command line parameters entered incorrectly.");
  }
  }
 
  public void run() {
    
  try {
	    
  File file = new File("in1.txt");
  File file1 = new File("in2.txt");
  File file2 = new File("in3.txt");
  File file3 = new File("out.txt");

  FileWriter writer = new FileWriter(file);
  FileWriter writer1 = new FileWriter(file1);
  FileWriter writer2 = new FileWriter(file2);
  FileWriter writer3 = new FileWriter(file3);
  
  int[]arr = {1,3,-4,7,12}; //создаём три неотсортированных массива, для файлов
  int[]arr1 = {1,8,-1,0,16};
  int[]arr2 = {2,19,6,17,5};
  
  selectionSort(arr); //предварительно сортируем их методом выбора (если я правильно понял задание)
  selectionSort(arr1);
  selectionSort(arr2);
  
  System.out.println("Three sorted arrays:");

  for(int i=0;i<arr.length;i++) //выводим три отсортированных массива
  System.out.print(arr[i]+" ");
  System.out.println();
 
  for(int i=0;i<arr1.length;i++)
  System.out.print(arr1[i]+" ");
  System.out.println();
 
  for(int i=0;i<arr2.length;i++)
  System.out.print(arr2[i]+" ");
  System.out.println();
  
  for(int i:arr) //наполняем ими три файла
  writer.write(i+"\n");
  
  for(int i:arr1) 
  writer1.write(i+"\n");
  
  for(int i:arr2) 
  writer2.write(i+"\n");
 
  writer.flush();
  writer.close();
  writer1.flush();
  writer1.close();
  writer2.flush();
  writer2.close();
  
  FileReader fr = new FileReader(file); //считываем файлы
  FileReader fr1 = new FileReader(file1);
  FileReader fr2 = new FileReader(file2);
  Scanner scr = new Scanner(fr);
  Scanner scr1 = new Scanner(fr1);
  Scanner scr2 = new Scanner(fr2);

  ArrayList<Integer>list = new ArrayList<>();//создаём список, для измерения размера массива
                                             //добавляем содержимое файлов в список
  while (scr.hasNextInt()) 
  list.add(scr.nextInt());
      
  while (scr1.hasNextInt()) 
  list.add(scr1.nextInt());
      
  while (scr2.hasNextInt()) 
  list.add(scr2.nextInt());
      
  System.out.println("\n"+"Output a common array (unsorted):");
      
  int[]array = new int[list.size()];
      
  for (int i=0;i<list.size();i++) {
  array[i]=list.get(i);
  System.out.print(array[i]+" ");// выводим общий массив (неотсортированный)
  }
  
  System.out.println();
      
  mergeSort(array,list.size());// выполняем сортировку слиянием
       
  for(int i:array) 
  writer3.write(i+"\n");// наполняем итоговый файл
      
  System.out.println("\n"+"Output a common array (sorted):");
      
  for(int i=0;i<array.length;i++)
  System.out.print(array[i]+" ");// выводим общий массив (отсортированный)
      
  writer3.flush();
  writer3.close();

  fr.close();
  fr1.close();
  fr2.close(); 
  scr.close();
  scr1.close();
  scr2.close();
  }
	
  catch (ArrayIndexOutOfBoundsException e) {
  System.err.println("Going beyond the array."); 
  }
  catch (IndexOutOfBoundsException e) {
  System.err.println("Invalid value of an array element."); 
  }
  catch(FileNotFoundException e) {
  System.err.print("File not found.");
  }
  catch(IOException e) {
  System.err.println("Data entry error.");
  }
  }
  
  public static void mergeSort(int[]src,int n) {//для разборки массива до одного элемента
  if(n < 2)
  return;

  int mid = n/2;
  int[]left = new int[mid];
  int[]right = new int[n-mid];

  System.arraycopy(src,0,left,0,mid);
  System.arraycopy(src,mid,right,0,n-mid);

  mergeSort(left,mid);
  mergeSort(right,n-mid);

  merge(src,left,right,mid,n-mid);
  }
  
  public static void merge(int[]src,int[]left,int[]right,int leftLength,int rightLength) {//для сборки и сортировки массива
  int k = 0,i = 0,j = 0;

  while (i<leftLength&&j<rightLength) {
  if (left[i] <= right[j]) 
  src[k++] = left[i++];
  else src[k++] = right[j++];
  }

  while (i < leftLength) {
  src[k++] = left[i++];
  }

  while (j < rightLength) {
  src[k++] = right[j++];
  }
  }
  
  public static void selectionSort(int[]list) {//для предварительной сортировки
  for (int i = 0; i < list.length - 1; i++) { 
   
  int currentMin = list[i];
  int currentMinIndex = i;

  for (int j = i + 1; j < list.length; j++) {
  if (currentMin > list[j]) {
  currentMin = list[j];
  currentMinIndex = j;
  }
  }

  if (currentMinIndex != i) { 
  list[currentMinIndex] = list[i];
  list[i] = currentMin;
  }
  }
  }
}

