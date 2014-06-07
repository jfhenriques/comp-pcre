-----------------------------------
1. COMPOSIÇÃO DO GRUPO
-----------------------------------
Turma 3 - Grupo 2
Diogo Almeida - ei12003@fe.up.pt
João Henriques - ei11026@fe.up.pt
João Monteiro - ei11055@fe.up.pt
Maria João Soutelo - ei09034@fe.up.pt

-----------------------------------
2. DESCRIÇÃO DO PROJETO
-----------------------------------
> #15 regx2auto
Ferramenta com a capacidade de ler expressões especificadas utilizando o formato PCRE e que constrói os autómatos correspondentes.

-----------------------------------
3. MANUAL DE UTILIZAÇÃO
-----------------------------------
Primeiro deve-se executar o ficheiro "Parser.jjt", gerando depois código Java recorrendo a JavaCC para o ficheiro "Parser.jj". Depois disso deve-se compilar o código Java gerado e proceder à execução do ficheiro "EntryPoint.java".
Em adição ao já referido, é necessário instalar o programa GraphViz (sugestão de site para download: http://www.graphviz.org/Download..php). Após a instalação, o ficheiro "GraphViz.java" deverá ser editado de modo a que a String TEMP_DIR contenha o diretório de uma pasta temporária já existente, e a String DOT o diretório onde foi instalado o programa (o caminho deverá terminar em ".../bin/dot.exe").
De notar que para a correta execução do projeto deverão existir duas pastas, "output" e "tmp", no mesmo diretório da pasta "src". Os autómatos gerados serão guardados na pasta "output".