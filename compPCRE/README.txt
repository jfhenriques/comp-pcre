-----------------------------------
1. COMPOSI��O DO GRUPO
-----------------------------------
Turma 3 - Grupo 2
Diogo Almeida - ei12003@fe.up.pt
Jo�o Henriques - ei11026@fe.up.pt
Jo�o Monteiro - ei11055@fe.up.pt
Maria Jo�o Soutelo - ei09034@fe.up.pt

-----------------------------------
2. DESCRI��O DO PROJETO
-----------------------------------
> #15 regx2auto
Ferramenta com a capacidade de ler express�es especificadas utilizando o formato PCRE e que constr�i os aut�matos correspondentes.

-----------------------------------
3. MANUAL DE UTILIZA��O
-----------------------------------
Primeiro deve-se executar o ficheiro "Parser.jjt", gerando depois c�digo Java recorrendo a JavaCC para o ficheiro "Parser.jj". Depois disso deve-se compilar o c�digo Java gerado e proceder � execu��o do ficheiro "EntryPoint.java".
Em adi��o ao j� referido, � necess�rio instalar o programa GraphViz (sugest�o de site para download: http://www.graphviz.org/Download..php). Ap�s a instala��o, o ficheiro "GraphViz.java" dever� ser editado de modo a que a String TEMP_DIR contenha o diret�rio de uma pasta tempor�ria j� existente, e a String DOT o diret�rio onde foi instalado o programa (o caminho dever� terminar em ".../bin/dot.exe").
De notar que para a correta execu��o do projeto dever�o existir duas pastas, "output" e "tmp", no mesmo diret�rio da pasta "src". Os aut�matos gerados ser�o guardados na pasta "output".