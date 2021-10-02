import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

/***********************************
Autor: Pablo Assunção Costa
Codigo simples com cifras classicas
**********************************/

public class cifras{

	public static void main(String[] args) {
		cifrar();
	}

	public static void cifrar(){
		Scanner opcao = new Scanner(System.in);
		//cria as opcoes de cifragem para que o usuario escolha
		System.out.println("Selecione uma opcao: ");
		System.out.println("1 - Cifra de cesar");
		System.out.println("2 - Cifra de transposição colunar");
		System.out.println("3 - Cifra de Vigenere");
		System.out.println("4 - Cifra de Playfair\n");

		int op = opcao.nextInt(); //recebe a opcao

		switch (op){ //redireciona para para o metodo que correspode a opcao selecionada
			case 1:
				cesar();
				break;
			case 2:
				colunar();
				break;
			case 3:
				vigenere();
				break;
			case 4:
				playfair();
				break;
			default:
				System.out.println("Opcao invalida tente denovo");
				break;
		} 
		cifrar(); //chama novamente este metodo para que aja outra cifragem
	}


	public static void cesar(){
		//scanner para a mensagem
        Scanner message = new Scanner(System.in);
        //scanner para a chave
        Scanner key = new Scanner(System.in);

        char ascii; //caractere para controle de cifragem

        String cesar = ""; //string que ira receber a mensagem criptografada

        System.out.println("Digite a mensagem: ");
        String mens = message.nextLine();
        System.out.println("Digite a CHAVE: ");//chave será um número que deslocará a mensagem pela sua quantidade (msg 'a' e chave = 2, cifra= 'c')
        int chave = key.nextInt();

        if(chave>=0){
        	 while (chave >= 26) {//chave tem que ter o tamanho do alfabeto
            	chave = chave - 26; //enquanto >=26 remove 26 para ficar dentro do alfabeto (Ex: 56-26=30 -> 30-26=4 -> chave = 4)
       		 }
        }else{
        	 while (chave <= -26) {//chave tem que ter o tamanho do alfabeto
            	chave = chave + 26; //enquanto >=26 remove 26 para ficar dentro do alfabeto (Ex: 56-26=30 -> 30-26=4 -> chave = 4)
       		 }
        }
       
       //prepara a mensagem removendo os espacos
	    mens = mens.replace(" ","");

        for (int i = 0; i < mens.length(); i++) {//passa em cada letra da mensagem

        	if(chave >=0){  //Caso a chave seja positiva esta cifrando
        		//Tratamento Letras minusculas  
	            if (mens.charAt(i) >= 97 && mens.charAt(i) <= 122) {//letras minusculas de acordo com a tabela ASCII
	                if ((int) (mens.charAt(i) + chave) > 122) {//se a soma da chave com o caracter estiver fora das minusculas
	                    ascii = (char) ((mens.charAt(i) + chave)-122); //recebe o char fora das minusculas e remove o valor maximo        
	                    cesar += (char) (96 + ascii); //adiciona o valor minimo para achar o valor equivalente nas minusculas
	                } else {
	                    cesar += (char) (mens.charAt(i) + chave); //se a soma estiver dentro das minusculas soma o caracter com a chave
	                }
	            }
	   			//Tratamento Letras maiusculas
	            if (mens.charAt(i) >= 65 && mens.charAt(i) <= 90) {//letras maiusculas de acordo com a tabela ASCII
	                if (mens.charAt(i) + chave > 90) {//se a soma da chave com o caracter estiver fora das maiusculas
	                    ascii = (char) ((mens.charAt(i) + chave)-90); //recebe o char fora das maiusculas e remove valor maximo
	                    cesar += (char) (64 + ascii); //adiciona valor minimo para achar o valor equivalente nas maiusculas
	                } else {
	                    cesar += (char) (mens.charAt(i) + chave); //se a soma estiver dentro das maiusculas soma o caracter com a chave
	                }
	            }
        	}
         	else{ //Caso a chave seja negativa esta decriptando
	            if (mens.charAt(i) >= 97 && mens.charAt(i) <= 122) {//letras minusculas de acordo com a tabela ASCII
	                if ((int) (mens.charAt(i) + chave) < 97) {//se a soma da chave com o caracter estiver fora das minusculas
	                    ascii = (char) ((mens.charAt(i) + chave)+122); //recebe o char fora das minusculas e adiciona o valor maximo        
	                    cesar += (char) (ascii-96); //remove o valor minimo para achar o valor equivalente nas minusculas
	                } else {
	                    cesar += (char) (mens.charAt(i) + chave); //se a soma estiver dentro das minusculas soma o caracter com a chave
	                }
	            }
		   		//Tratamento Letras maiusculas
	            if (mens.charAt(i) >= 65 && mens.charAt(i) <= 90) {//letras maiusculas de acordo com a tabela ASCII
	                if (mens.charAt(i) + chave < 65) {//se a soma da chave com o caracter estiver fora das maiusculas
	                    ascii = (char) ((mens.charAt(i) + chave)+90); //recebe o char fora das maiusculas e adiciona valor maximo
	                    cesar += (char) (ascii-64); //remove valor minimo para achar o valor equivalente nas maiusculas
	                } else {
	                    cesar += (char) (mens.charAt(i) + chave); //se a soma estiver dentro das maiusculas soma o caracter com a chave
	                }
	            }
        	}
        }

        System.out.println("Cifra de cesar: "); //imprime a cifra
        System.out.println(cesar+"\n");
	}

	public static void colunar(){
		//Scaner para opcao
		Scanner op = new Scanner(System.in);
		//scanner para a mensagem
        Scanner message = new Scanner(System.in);
        //scanner para a chave
        Scanner key = new Scanner(System.in);

        String cifrado = "";

        System.out.println("Cifra de transposicao colunar");
        System.out.println(" 1 - Cifrar  |   2 - Decifrar");
        int opcao = op.nextInt();

        System.out.println("Digite a mensagem: ");
        String mens = message.nextLine();
        System.out.println("Digite a CHAVE: ");
        String chave = key.nextLine();

        int linha= mens.length()/chave.length();
        int coluna = chave.length();
        int next = 0; //controla o  proximo caractere da mensagem

        Character[][] colunar = new Character [linha][coluna]; //matriz que armazena a mensagem

        char[] str = chave.toCharArray();
        Arrays.sort(str);//ordena alfabeticamente a chave
        int[] ordem = new int[coluna]; //int que recebe a ordem das colunas

        //cria uma ordem alfabetica a partir da chave para ser montada a mensagem cifrada
        // as colunas colocadas na mensagem cifrada entram em ordem alfabetica
        // ex: CHAVE -> ACEHV => mensagem = coluna(A)+ coluna(C)+... 
        for(int i=0 ; i<coluna;  i++){
        	for(int j=0 ; j<coluna;  j++){
        		if(str[i]==chave.charAt(j)){
        			ordem[i]=j;
        			break;
        		}
        	}     	
        }

        //prepara a mensagem removendo os espacos
	    mens = mens.replace(" ","");

        if(opcao==1){
        	//prreenche a matriz com a mensagem
	        for(int i=0 ; i<linha;  i++){
	        	for(int j=0 ; j<coluna; j++){       		
	        		if(next < mens.length()){
	        			colunar[i][j] = mens.charAt(next);//coloca o char na posicao na matriz
	        			next++;
	        		}else{// quando tiver completado a matriz com a mensagem, se ouver espaco coloca letras aleatorias da chave
	        			colunar[i][j] = chave.charAt( (int) (Math.random() * chave.length()-1)); 
	        		}       		
	        	}
	        }

	        //monta a mensagem cifrada
	        for(int i=0 ; i<coluna;  i++){
	        	for(int j=0 ; j<linha; j++){
	        		//pega por coluna do array e coloca na mensagem
	        		cifrado += colunar[j][ordem[i]];
	        	}
	        	cifrado += " "; //adiciona espaco apos cada coluna
	        }

	        System.out.println("\nCifrar transposicao colunar: "); 
        }
        
        if(opcao==2){
        	//DEcifrar
	        int prox = 0 ;

	        for(int i=0 ; i<coluna;  i++){
	         	for(int j=0 ; j<linha; j++){
	        		if(prox < mens.length()){
	        			colunar[j][ordem[i]] = mens.charAt(prox);
	        			prox++;
	        		}
	        	}
	         }
	       
	        for(int i=0 ; i<linha;  i++){
	        	for(int j=0 ; j<coluna; j++){
	        		cifrado += colunar[i][j];//monta a mensagem decifrada
	           	}
	        }  	        
	        System.out.println("\nTransposicao colunar decifrado: "); 
        }
        System.out.println(cifrado+"\n"); //imprime a cifra
	}

	public static void vigenere(){
		//Scaner para opcao
		Scanner op = new Scanner(System.in);
		//scanner para a mensagem
        Scanner message = new Scanner(System.in);
        //scanner para a chave
        Scanner k = new Scanner(System.in);

        System.out.println("Cifra de Vigenere");
        System.out.println(" 1 - Cifrar  |   2 - Decifrar");
        int opcao = op.nextInt();

        System.out.println("Digite a mensagem: ");
        String msg = message.nextLine();
        System.out.println("Digite a CHAVE: ");
        String chave = k.nextLine();

        //strings para conter a chave
		String key = "";
		
		//grade de vigenere
	    char[][] vin = new char[26][26];

	    int inicio = 65; //valor inicial de cada linha da matriz
	    char linha = (char) inicio;

	    //strign que recebe a mensagem cifrada ou decifrada
	    String vigenere = "";

	     //constroi a matriz com as letras do alfabeto
	     for (int i=0; i<26 ; i++){
	      	for (int j=0; j<26 ; j++){
	      		if(linha > 90){ //se chegou no 'Z', volta para o 'A' para preencher a matriz
	      			linha = (char) 65;
	      		}
	      		vin[i][j] = linha; //coloca a letra na proxima posicao
	      		linha++; //passa para a proxima letra a ser inserida
	      	}
	      	inicio ++; //a proxima linha comeca com a letra da sequencia (linha 1= A | linha 2 = B)
	      	linha = (char) inicio;
	      }

	    //prepara a mensagem removendo os espacos
	    msg = msg.replace(" ","");

	    //prepara a chave repetindo-a ate que tenha o mesmo tamanho da mensagem
	    while(key.length()<msg.length()){//ate que tenha o mesmo tamanho
	    	for(int j=0; j<chave.length() ;j++){ //repete a chave
	    		if(key.length()<msg.length()){//se passsar do tamnho nao coloca os caracteres
	    			key += chave.charAt(j); 
	    		} 
	    	}
	    }

	    if(opcao==1){
		   	//cifrar
		   	for(int i = 0 ; i<msg.length(); i++){
		   		int col = (int) msg.charAt(i);
		   		int line = (int) key.charAt(i);
		   		vigenere += vin[line-65][col-65];
		   	}
		   	System.out.println("\nCifrar Vigenere: ");
	   	}
	   	
	   	if(opcao==2){
		    //decifrar
		   	for(int i = 0 ; i<key.length(); i++){
		       int line = (int) (key.charAt(i)-65);
		       for(int j=0; j<26 ;j++){
		            if(vin[line][j]==msg.charAt(i)){
		              vigenere += (char) (j+65);
		              break;
		            }
		       	}
	    	}
	    	System.out.println("\nVigenere Decifrado: ");
    	}

    	System.out.println(vigenere+ "\n");
	}

	public static void playfair(){
		//Scaner para opcao
		Scanner op = new Scanner(System.in);
		//scanner para a mensagem
        Scanner message = new Scanner(System.in);
        //scanner para a chave
        Scanner k = new Scanner(System.in);

        System.out.println("Cifra de Vigenere");
        System.out.println(" 1 - Cifrar  |   2 - Decifrar");
        int opcao = op.nextInt();

        System.out.println("Digite a mensagem: ");
        String mensagem = message.nextLine();
        System.out.println("Digite a CHAVE: ");
        String chave = k.nextLine();

        //alfabeto para preencher a matriz
		String alfabeto = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

		//matriz de playfair
	    char[][] matriz = new char[5][5];

	    int countKey = 0;
	    int count = 0;

	    String cifrado="";
	    //cria a matriz
	    for(int i=0; i<5 ; i++){
	      for(int j=0; j<5 ; j++){
	        if(countKey<chave.length()){
	          matriz[i][j] = chave.charAt(countKey);
	          if(chave.charAt(countKey)!='J'){//se for um 'J' nao precisa remover, o 'J' esta unido com o 'I' no alfabeto
	            alfabeto = alfabeto.replace(""+chave.charAt(countKey), ""); //remove as letras da chave q estao no alfabeto
	          }
	          countKey++;
	        }else{
	          matriz[i][j] = alfabeto.charAt(count);
	          count++;
	        }
	      }
	    }

	    //prepara a mensagem removendo os espacos
		mensagem = mensagem.replace(" ", "");
		//zera o contador para ser usado novamente
		count = 0 ;

		if(opcao==1){
			//CIFRAR
			while(count<mensagem.length()){
				if(count+1 >= mensagem.length()){//se for o ultimo char da mensagem
					cifrado += mensagem.charAt(count);//coloca ele mesmo na cifra
				}else{//senao, cifra ele e o seu par
					int[] a = lineCol(mensagem.charAt(count),matriz); //pega a posicao do char atual na matriz
					int[] b = lineCol(mensagem.charAt(count+1),matriz);//pega a posicao do proximo char na matriz
					if(a[0]==b[0]){//linhas iguais insere o valor da proxima coluna
						if(a[1]>3){//se for a ultima coluna, coloca o da primeira
							cifrado += matriz[a[0]][0];
						}else{//se nao coloca a proxima da direita
							cifrado += matriz[a[0]][a[1]+1];
						}

						if(b[1]>3){//se for a ultima coluna, coloca o da primeira
							cifrado += matriz[b[0]][0];
						}else{//se nao coloca a proxima da direita
							cifrado += matriz[b[0]][b[1]+1];
						}
					}else if(a[1]==b[1]){//colunas iguais insere o valor da proxima linha 
						if(a[0]>3){//se for a ultima linha, coloca o da primeira
							cifrado += matriz[0][a[1]];
						}else{//se nao coloca o da proxima linha
							cifrado += matriz[a[0]+1][a[1]];
						}
						if(b[0]>3){//se for a ultima linha, coloca o da primeira
							cifrado += matriz[0][b[1]];
						}else{//se nao coloca o da proxima linha
							cifrado += matriz[b[0]+1][b[1]];
						}
					}else{// linhas e colunas diferentes
						cifrado += matriz[a[0]][b[1]];
						cifrado += matriz[b[0]][a[1]];
					}	
				}
				count += 2;
			}
			System.out.println("\nCifra de playfair: ");
		}
		if(opcao==2){
			//DECIFRAR
			while(count<mensagem.length()){
				if(count+1 >= mensagem.length()){//se for o ultimo char da mensagem
					cifrado += mensagem.charAt(count);//coloca ele mesmo na cifra
				}else{//senao, cifra ele e o seu par
					int[] a = lineCol(mensagem.charAt(count),matriz); //pega a posicao do char atual na matriz
					int[] b = lineCol(mensagem.charAt(count+1),matriz);//pega a posicao do proximo char na matriz
					if(a[0]==b[0]){//linhas iguais insere o valor da proxima coluna
						if(a[1]<1){//se for a primeira coluna, coloca o da ultima
							cifrado += matriz[a[0]][4];
						}else{//se nao coloca a proxima da esquerda
							cifrado += matriz[a[0]][a[1]-1];
						}

						if(b[1]<1){//se for a  primeira coluna, coloca o da ultima
							cifrado += matriz[b[0]][4];
						}else{//se nao coloca a proxima da esquerda
							cifrado += matriz[b[0]][b[1]-1];
						}
					}else if(a[1]==b[1]){//colunas iguais insere o valor da proxima linha 
						if(a[0]<1){//se for a primeira linha, coloca o da ultima
							cifrado += matriz[4][a[1]];
						}else{//se nao coloca o da linha anterior 
							cifrado += matriz[a[0]-1][a[1]];
						}
						if(b[0]<1){//se for a primeira linha, coloca o da ultima
							cifrado += matriz[4][b[1]];
						}else{//se nao coloca o da linha anterior
							cifrado += matriz[b[0]-1][b[1]];
						}
					}else{// linhas e colunas diferentes
						cifrado += matriz[a[0]][b[1]];
						cifrado += matriz[b[0]][a[1]];
					}	
				}
				count += 2;
			}
			System.out.println("\nPlayfair decifrado: ");
		}

		System.out.println(cifrado+"\n");
	}

	public static int[] lineCol(char x, char[][] matriz){
		//recebe um char q vai ser buscado na matriz recebida
		//funcao auxilia playfair
		if(x=='J'){ // se ver um j coloca o i no lugar
			x= 'I';// para manter padrao do alfabeto
		}
		for(int i=0; i<5 ; i++){
      		for(int j=0; j<5 ; j++){
      			if(matriz[i][j] == x){
      				int[] retorno = {i,j};
      				return retorno;
      			}
      		}
      	}
      	return null;
	}
}