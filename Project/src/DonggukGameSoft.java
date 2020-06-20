//가장 효율성 높은 코드. 평균값을 pivot으로해 절반정도 퀵정렬
import java.util.Arrays;
import java.util.Scanner;
public class DonggukGameSoft {
	
	static int quicksort1(int[] data, int f, int p, int num) { //num은 pivot으로 하고싶은 값
		data[p+1]=num; //num을 pivot으로 설정하도록 맨 마지막에 두기
		int s = partition1(data, f, p+1); //pivot의 인덱스
		return s;}
	
	static int partition1(int[] data, int f, int p) { //pivot값 임의설정 partition
		int pivot = data[p]; 
		int i=f, j=p-1;
		while(i<=j) {
			if(pivot < data[i]) {
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
				j--; }
			else i++; }
		data[p] = data[i];
		data[i] = pivot;
		for (int a = i+1; a <= p; a++) { //pivot은 임의로 설정한 값이므로 하나씩 앞당기며 그 값 삭제
			data[a-1] = data[a]; }
		return i; }
	
	static void quicksort2(int[] data, int f, int p) {
		if(p <= f) return;
		int s = partition2(data, f, p);
		quicksort2(data, f, s-1);
		quicksort2(data, s+1, p);}
	
	static int partition2(int[] data, int f, int p) { //pivot값 원래배열끝 partition
		int pivot = data[p];
		int i=f, j=p-1;
		while(i<=j) {
			if(pivot < data[i]) {
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
				j--; }
			else i++; }
		data[p] = data[i];
		data[i] = pivot;
		return i; }
	
	//	data[s..e]에서 k번째로 작은 데이터를 찾는다.
	static int nth_element(int[] data, int s, int e, int k) {
		int p = partition2(data, s, e); //data[s..e]를 피봇값에 의해서 분리한다.
		if(k<=p-s) return nth_element(data, s, p-1, k); //k가 피봇보다 작은 그룹에 속해있는 경우
		if(k==p-s+1) return data[p];//k가 피봇인 경우
		else return nth_element(data, p+1, e, k-p+s-1); } //k가 피봇보다 큰 그룹에 속해있는 경우

	
	public static void main(String[] args) {
		Scanner s= new Scanner(System.in);
		System.out.print("사용자 수를 1,000,000 ~ 10,000,000 사이로 입력하세요 : ");
		int n = s.nextInt();
		
		System.out.print("몇 등이 궁금하신가요? : ");
		int a = s.nextInt();
		System.out.println("( "+(double)a/n*100+" %에 해당하는 등수 )");
		
		for(int k=1; k<=3; k++) {
			System.out.println("\n <<<"+k+" 번째 무결성 테스트 >>>");
			int[] data = new int[n + 1];
			int[] t = new int[n];
			double sum = 0;
			for(int i=0;i<n;i++) {
				data[i] = (int)((Math.random()*9000001)+1000000); //랜덤데이터 반복 생성 1,000,000~10,000,000
				t[i] = data[i]; //data배열=t배열
				sum = sum + (data[i] / 1000000.0); //숫자 크기가 커져서, 랜덤 시작숫자로 나누어서 랜덤데이터 모두 더함
			}
			
			//for(int i=0; i<data.length; i++) {
			//	if (avg==data[i]) {System.out.println(i+"번째");}}  여러번 테스트해봐도 평균값이면 중간부분. 30%밑으로 안내려감
			
			System.out.println("\n------작성코드 정렬------");
			long r = System.currentTimeMillis();
			int avg = (int)((sum / n) * 1000000.0); //다 더한 랜덤데이터의 평균 구하기
			//System.out.println("평균값 : " + avg);	
			int p_avg = quicksort1(data, 0, n-1, avg); //평균을 pivot으로 했을 때 전체 퀵정렬 후 pivot의 인덱스

			quicksort2(data, p_avg, n-1); //평균값 중간 정도부터 부터 1등까지 재귀적 퀵정렬해서 전부 정렬
			
			System.out.println(a+"등: "+data[n-a]+"점");
			System.out.println("elapsed time : "+(System.currentTimeMillis()-r));
			System.out.printf("1~5등 : %d, %d, %d, %d, %d \n", data[n-1], data[n-2], data[n-3], data[n-4], data[n-5]);
			System.out.printf("6등 : %d, 10등 : %d,  10per : %d,  20per : %d,  30per : %d \n\n", data[n-6], data[n-10], data[(int)(n*0.9)+1], data[(int)(n*0.8)+1], data[(int)(n*0.7)+1]);
			System.out.printf("정렬안한  60per : %d,  90per : %d \n", data[(int)(n*0.4)+1], data[(int)(n*0.1)+1]);
			
			System.out.println("\n------자바 기본정렬------");
			r = System.currentTimeMillis();
			Arrays.sort(t);
			
			System.out.println(a+"등: "+t[n-a]+"점");
			System.out.println("elapsed time : "+(System.currentTimeMillis()-r));
			System.out.printf("1~5등 : %d, %d, %d, %d, %d \n", t[n-1], t[n-2], t[n-3], t[n-4], t[n-5]);
			System.out.printf("6등 : %d, 10등 : %d,  10per : %d,  20per : %d,  30per : %d \n\n", t[n-6], t[n-10], t[(int)(n*0.9)+1], t[(int)(n*0.8)+1], t[(int)(n*0.7)+1]);
			System.out.printf("기본정렬된  60per : %d,  90per : %d \n", t[(int)(n*0.4)+1], t[(int)(n*0.1)+1]);
			
			if (a==1) System.out.println("\n => 다이아몬드 2000개");
			else if (a==2) System.out.println("\n => 다이아몬드 1800개");
			else if (a==3) System.out.println("\n => 다이아몬드 1600개");
			else if (a==4) System.out.println("\n => 다이아몬드 1400개");
			else if (a==5) System.out.println("\n => 다이아몬드 1200개");
			else if (6<=a && a<=10) System.out.println("\n => 다이아몬드 1000개"); //6~10등
			else if (a<=n*0.1) System.out.println("\n => 다이아몬드 800개"); //11~상위10%
			else if (a<=n*0.2) System.out.println("\n => 다이아몬드 600개"); //상위10%~상위20%
			else if (a<=n*0.3) System.out.println("\n => 다이아몬드 400개"); //상위20%~상위30%
			else System.out.println("\n => 다이아몬드 200개"); //나머지
		}
	}
}