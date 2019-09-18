# Oliver Publish and Subscriber

Sistema publish and subscriber desenvolvido com RMI do java. Neste sistema, o publicador pode oferecer um curso, e alunos podem se inscrever neste curso, simulando uma turma. Quando o publicador manda uma mensagem para o curso, todos os alunos desta turma recebem a mensagem.

## Estrutura

1. Cadastro

Para isto, é utilizado uma interface remota (Oliver) que é a que permite que o publicador se cadastre, ofereça um curso, e alunos se inscrevam neste curso. Esta interface é implementada pela classe Server. Cursos, publicadores e inscritos ficam armazenados neste servidor. A classe Publisher é a classe que modela o publicador, e a classe Subscriber é a classe que modela o inscrito. É importante dizer que o Subscriber possui uma lista com os cursos nos quais ele está inscrito e uma caixa de entrada, que possui o id do curso a que aquela mensagem pertence, e a mensagem.

As classes de interação com o usuário são Client (para o publicador) e ClientSubs (para o inscrito). Estas classes são as que possuem métodos main - além do servidor - e apresentam um menu de opções para o usuário.

A classe Client apresenta as seguintes opções:

- Cadastrar um publisher
- Cadastrar um curso
- Ver todos os publishers
- Ver todos os cursos
- Enviar mensagem para um curso
- Sair

OBS: A terceira e a quarta opção são excecutadas por uma thread para que o usuário possa continuar no menu enquanto a thread faz as impressões.

A classe ClientSubs apresenta as seguintes opções:

- Cadastrar um novo subscriber
- Se inscrever em um curso
- Remover um curso
- Listar todos os meus cursos
- Visualizar caixa de entrada
- Sair

2. Recebimento de mensagens assíncronas

Para o recebimento de mensagens, a classe Inbox foi implementada, e ela funciona como um servidor, no qual a mensagem dos incritos é impressa, e como cliente da classe Server. A classe Inbox implementa a segunda interface remota (Dodger) que possui apenas um método de listar mensagens. Quando o publicador envia uma mensagem para um curso, o Server chama o método `listarMensagens()` implementado pelo Inbox e as mensagens são impressas.

## Executando o programa

OBS: a partir do passo 3, em ambos os sistemas, deve-se abrir uma nova janela do terminal do Linux ou do prompt de comandos do Windows no mesmo diretório em que estão os arquivos deste repositório.

### Linux

1. Abra o terminal no diretório com os arquivos deste repositório e compile todos os arquivos.java com o comando abaixo:

`javac *.java`

2. Execute o RMI Registry com o seguinte comando:

`rmiregistry`

3. Execute o servidor:

`java Server`

4. Execute a classe Client (para o publicador):

`java Client`

5. Execute a classe ClientSubs (para o inscrito):

`java ClientSubs`

6. Execute o inbox:

`java Inbox`


### Windows

1. Abra o prompt de comando do Windows no diretório onde estão os arquivos deste repositório e compile os arquivos.java:

`javac *.java`

2. Execute o RMI Registry:

`start rmiregistry`

3. Execute o servidor:

`start java Server`

4. Execute o cliente para o publicador:

`start java Client`

5. Execute o cliente para o inscrito:

`start java ClientSubs`

6. Execute o Inbox:

`start java Inbox`