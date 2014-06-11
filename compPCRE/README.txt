-----------------------------------
1. COMPOSI��O DO GRUPO
-----------------------------------
Turma 3 - Grupo 2
Diogo Almeida - ei12003@fe.up.pt - 25%
Jo�o Henriques - ei11026@fe.up.pt - 25%
Jo�o Monteiro - ei11055@fe.up.pt - 25%
Maria Jo�o Soutelo - ei09034@fe.up.pt - 25%



-----------------------------------
2. DESCRI��O DO PROJETO
-----------------------------------
> #15 regx2auto
Ferramenta com a capacidade de ler express�es regulares no formato PCRE que constr�i os aut�matos correspondentes c�digo JAVA para validar input com base na express�o regular.

Da linguagem pcre foi implementada a 100% os quantifiers, p.ex: a*, a{3}, a?, a+, a{1,5}
Foi tamb�m implementado os "ou"s (aaa|bbb|ccc) mas em alguns testes falharam, quando misturados com quantifiers.
O ponto "." tamb�m � valido, signigicando apenas um qualquer caract�r.
Para aceitar um ponto em si, � preciso insirir uma barra antes do mesmo "\.". O mesmo aplica-se aos parentisis e chavetas, etc, tendo de ficar "\{", "\}", "\(", "\)", ...

-----------------------------------
3. MANUAL DE COMPILA��O
-----------------------------------
Primeiro deve-se executar o ficheiro "Parser.jjt", gerando c�digo do compilador em Java recorrendo ao JavaCC (para efeitos de incompatibilidades, o c�digo gerado foi inclu�do no zip).
Depois deve-se compilar o projecto em si e proceder � execu��o do ficheiro "EntryPoint.java".
Em adi��o ao j� referido, � necess�rio instalar o programa GraphViz (sugest�o de site para download: http://www.graphviz.org/Download..php).
Ap�s a instala��o, o ficheiro "GraphViz.java" dever� ser editado de modo a que a String TEMP_DIR contenha o diret�rio de uma pasta tempor�ria para coloca��o de c�digo tempor�rio, e a String DOT o diret�rio onde foi instalado o programa (o caminho dever� terminar em ".../bin/dot.exe").
De notar que para a correta execu��o do projeto dever�o existir duas pastas, "output" e "tmp", no mesmo diret�rio em que a pasta "src" se encontra.


-----------------------------------
4. MANUAL DE EXECU��O
-----------------------------------
Para facilitar foi gerado o JAR que pode ser executado na consola da seguinte forma
java -jar pcre.jar PCRE_EXPRESSION OUTPUT_FILE_NAME GRAPHVIZ_DOTTY_EXECUTABLE

Os aut�matos gerados ser�o guardados na pasta "output", bem como o c�digo java gerado pelo compilador.
Deve existir uma pasta "tmp" no mesmo direct�rio para coloca��o de ficheiro tempor�rios gerados pelo graphviz.

no caso de uma transi��o epsilon esta ser� representada por < E > nas imagens, e no caso do ponto (qualquer caracter) por < ANY >


-----------------------------------
5. Alguns exemplos
-----------------------------------
java -jar pcre.jar "htp://(www|blog)\.example.com" OUTPUT_FILE_NAME GRAPHVIZ_DOTTY_EXECUTABLE
java -jar pcre.jar "a(b(c+d)*e)+f" OUTPUT_FILE_NAME GRAPHVIZ_DOTTY_EXECUTABLE
entre outros.

De notar que para validar uma barra "\" hardcoded no c�digo java, esta tem de ser duplamente comentada, logo para validar um input como por exemplo "c:\temp" lido directamente do System.in, a string hardcoded teria de ser gravada "c:\\\\temp" para compilar a express�o regular "c:\\temp".
