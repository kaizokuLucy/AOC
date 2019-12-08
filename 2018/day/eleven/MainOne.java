package day.eleven;

public class MainOne {

	public static void main(String[] args) {
		int serialNum = 2568;
		int[][] field = new int[301][301];

		int res = 0;
		for (int i = 1; i <= 300; i++) {
			for (int j = 1; j <= 300; j++) {
				int rackID = j + 10;
				res = (rackID * i) + serialNum;
				res = res * rackID;
				res = (res % 1000) / 100;
				res = res - 5;
				field[j][i] = res;
			}
		}

//		System.out.println(field[3][5]);
//		System.out.println(field[122][79]);
//		System.out.println(field[217][196]);
//		System.out.println(field[101][153]);

		int sum = 0;
		int max = 0;
		int x = 0, y = 0, size = 0;
		for (int s = 3; s < 300; s++) {
			for (int i = 1; i <= 301-s; i++) {
				for (int j = 1; j <= 301-s; j++) {
					for (int k = 0; k < s; k++) {
						for (int l = 0; l < s; l++) {
							sum += field[i + l][j + k];
						}
					}
					if (sum > max) {
						max = sum;
						x = i;
						y = j;
						size = s;
					}
					sum = 0;
				}
			}
		}
		System.out.println(max + "\n" + "(X,Y) " + "(" + x + "," + y + "," + size + ")");

	}
}
