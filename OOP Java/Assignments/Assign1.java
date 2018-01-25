/*
 * Shahir Chowdhury
 * 2016-05-08
 * Assign1.java
 *
 * This program decodes the top secret mathematical expression, TOO + TOO + TOO + TOO = GOOD,
 * where each letter represents some distinct numerical value
*/

public class Assign1{
	public static void main(String[] args){
		for (int t = 9;t > -1;t--){
			for (int o = 9;o > -1;o--){
				for (int g = 9;g > -1;g--){
					for (int d = 9;d > -1;d--){
						if (d == (400 * t - 1000 * g - 66 * o)){	//equation to prove that the solution works
							if (d != t && d != g && d != o && t != g && t != o && g != o){	//solution is only valid if each number is unique
								System.out.printf("T = [%d], O = [%d], G = [%d], D = [%d]", t, o, g, d);
								return;
							}
						}
					}
				}
			}
		}
		return;
	}
}