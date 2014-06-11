-----------------------------------
1. COMPOSIÇÃO DO GRUPO
-----------------------------------
Turma 3 - Grupo 2
Diogo Almeida - ei12003@fe.up.pt - 25%
João Henriques - ei11026@fe.up.pt - 25%
João Monteiro - ei11055@fe.up.pt - 25%
Maria João Soutelo - ei09034@fe.up.pt - 25%



-----------------------------------
2. DESCRIÇÃO DO PROJETO
-----------------------------------
> #15 regx2auto
Ferramenta com a capacidade de ler expressões regulares no formato PCRE que constrói os autómatos correspondentes código JAVA para validar input com base na expressão regular.

Da linguagem pcre foi implementada a 100% os quantifiers, p.ex: a*, a{3}, a?, a+, a{1,5}
Foi também implementado os "ou"s (aaa|bbb|ccc) mas em alguns testes falharam, quando misturados com quantifiers.
O ponto "." também é valido, signigicando apenas um qualquer caractér.
Para aceitar um ponto em si, é preciso insirir uma barra antes do mesmo "\.". O mesmo aplica-se aos parentisis e chavetas, etc, tendo de ficar "\{", "\}", "\(", "\)", ...

-----------------------------------
3. MANUAL DE COMPILAÇÃO
-----------------------------------
Primeiro deve-se executar o ficheiro "Parser.jjt", gerando código do compilador em Java recorrendo ao JavaCC (para efeitos de incompatibilidades, o código gerado foi incluído no zip).
Depois deve-se compilar o projecto em si e proceder à execução do ficheiro "EntryPoint.java".
Em adição ao já referido, é necessário instalar o programa GraphViz (sugestão de site para download: http://www.graphviz.org/Download..php).
Após a instalação, o ficheiro "GraphViz.java" deverá ser editado de modo a que a String TEMP_DIR contenha o diretório de uma pasta temporária para colocação de código temporário, e a String DOT o diretório onde foi instalado o programa (o caminho deverá terminar em ".../bin/dot.exe").
De notar que para a correta execução do projeto deverão existir duas pastas, "output" e "tmp", no mesmo diretório em que a pasta "src" se encontra.


-----------------------------------
4. MANUAL DE EXECUÇÃO
-----------------------------------
Para facilitar foi gerado o JAR que pode ser executado na consola da seguinte forma
java -jar pcre.jar PCRE_EXPRESSION OUTPUT_FILE_NAME GRAPHVIZ_DOTTY_EXECUTABLE

Os autómatos gerados serão guardados na pasta "output", bem como o código java gerado pelo compilador.
Deve existir uma pasta "tmp" no mesmo directório para colocação de ficheiro temporários gerados pelo graphviz.

no caso de uma transição epsilon esta será representada por < E > nas imagens, e no caso do ponto (qualquer caracter) por < ANY >


-----------------------------------
5. Alguns exemplos
-----------------------------------
java -jar pcre.jar "htp://(www|blog)\.example.com" OUTPUT_FILE_NAME GRAPHVIZ_DOTTY_EXECUTABLE
java -jar pcre.jar "a(b(c+d)*e)+f" OUTPUT_FILE_NAME GRAPHVIZ_DOTTY_EXECUTABLE
entre outros.

De notar que para validar uma barra "\" hardcoded no código java, esta tem de ser duplamente comentada, logo para validar um input como por exemplo "c:\temp" lido directamente do System.in, a string hardcoded teria de ser gravada "c:\\\\temp" para compilar a expressão regular "c:\\temp".
