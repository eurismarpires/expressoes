import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrefixToInfix {
	public static String prefixToInfix(String expressao){
		//String str = "(* 4,473686  ( + 5,080500 5,466386 ))";	
		//str = "( *  ( *  ( / 5,026922 X )   ( + 4,811583 5,456033 )  )   ( -  ( / 5,027035 5,578136 )   ( - 5,055028 X )  )  )";
		String str = expressao.replace("RandomDoublex", "");
		str = expressao.replace("|", "");
		System.out.println(str);
		List<String> lista = new ArrayList<>();
		while (str.trim() != "" && str.length() > 0) {
			// String a = str.substring(1,1).equals("+");			
			if ((str.substring(0, 1).equals("(")) || (str.substring(0, 1).equals(")"))
					|| (str.substring(0, 1).equals("+")) || (str.substring(0, 1).equals("-"))
					|| (str.substring(0, 1).equals("*")) || (str.substring(0, 1).equals("/")))
					 {
				lista.add(str.substring(0, 1));
				str = str.substring(1);

			} else if (str.substring(0, 1).equals(" ")) {
				str = str.substring(1);
			} else {
				int pos = str.indexOf(" ");
				try {
					if(pos != -1){
						String s = str.substring(0, pos);
						lista.add(s);
						str = str.substring(pos + 1);
					}else{
						lista.add(str);
						str = "";
					}
						
					// System.out.println(s);
					
					
				} catch (Exception e) {
					System.out.println("erro: " + str);
					str = "";
					
				}
			}
		}		
		//for (String s : lista) {
		//	System.out.println(s);
		//}
		//System.out.println("Reverse");
		Stack<String> pilha = new Stack<>();
		for(int i = lista.size() - 1; i >= 0; i--){
			//System.out.print(lista.get(i) + " ");
			String s = lista.get(i);
			if(!(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) && !(s.equals("("))){
				pilha.push(s);
			}else if(s.equals("(")){
				String aux = lista.get(i);
				String exp = pilha.pop();
				aux = aux + exp + pilha.pop();
				pilha.push(aux);
			}else{
				String aux = pilha.pop();
				String op = lista.get(i);
				aux = aux + " " + op + " " + pilha.pop();
				pilha.push(aux);
			}
		}	
	//	System.out.println("\nValor da pilha");
	//	System.out.println(pilha.peek());		
		return pilha.peek();
	}
	public static void main(String arg[]) {
		String str = "( *  ( *  ( / 5,026922 X )   ( + 4,811583 5,456033 )  )   ( -  ( / 5,027035 5,578136 )   ( - 5,055028 X )  )  )";
		PrefixToInfix pf = new PrefixToInfix();		
		System.out.println("Expressão Gerada:\n"+pf.prefixToInfix(str));
	}
}
