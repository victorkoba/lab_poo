# Registro de aprendizagem

Três erros que enfrentei ao construir o núcleo do banco digital, como resolvi e o que faria diferente.

## 1. `saldo has private access in Conta`

**O erro:** depois de encapsular a classe `Conta` (etapa 4), deixei o atributo `saldo` como `private`, mas o código que já existia ainda fazia `conta.saldo = 1000` e `conta.saldo -= valor` direto de fora da classe. O `javac` parou de compilar:

```
error: saldo has private access in Conta
```

**Como resolvi:** troquei os acessos diretos pelos métodos da própria classe: `getSaldo()` para ler, `depositar()` e `sacar()` para alterar. A validação (valor positivo, saldo suficiente) ficou dentro desses métodos, em um só lugar. Usei `javap -p` para conferir quais membros ficaram privados.

**O que faria diferente:** criaria a classe já com os atributos `private` e só os métodos necessários públicos, em vez de deixar tudo aberto e encapsular depois. Assim nenhum código passa a depender do estado interno da classe.

## 2. `unreported exception SaldoInsuficienteException`

**O erro:** quando criei a `SaldoInsuficienteException` (etapa 5), fiz ela estender `Exception`. Ela virou uma exceção *checada*, e todo método que chamava `sacar()` deixou de compilar:

```
error: unreported exception SaldoInsuficienteException; must be caught or declared to be thrown
```

**Como resolvi:** entendi a diferença entre exceção checada (`Exception`, obrigatório tratar com `try/catch` ou declarar com `throws`) e não checada (`RuntimeException`). Como saldo insuficiente é uma violação de regra de negócio que quem chama pode evitar consultando o saldo, passei a estender `RuntimeException`. No `App` tratei com `try/catch`, mostrando "Falhou: ..." em vez de deixar o programa quebrar.

**O que faria diferente:** decidiria o tipo da exceção antes de espalhar chamadas pelo código, pensando em quem vai tratá-la, e escreveria logo um teste com `assertThrows` para o caso de saldo insuficiente.

## 3. `no main manifest attribute` ao rodar o `.jar`

**O erro:** o `mvn clean package` gerou o `.jar` com sucesso, mas ao executar:

```
$ java -jar target/banco-digital-1.0-SNAPSHOT.jar
no main manifest attribute, in target/banco-digital-1.0-SNAPSHOT.jar
```

O build "passou", mas o arquivo não era executável: o `MANIFEST.MF` dentro do jar não dizia qual classe tem o `main`.

**Como resolvi:** primeiro confirmei que o código estava certo rodando `java -cp target/banco-digital-1.0-SNAPSHOT.jar br.com.fintech.App`, que funcionou. Depois configurei o `maven-jar-plugin` no `pom.xml` com `<mainClass>br.com.fintech.App</mainClass>`, rodei `mvn package` de novo e o `java -jar` funcionou. No caminho, cheguei a colar o XML do plugin no terminal em vez de no `pom.xml`, o que me lembrou de prestar atenção em *onde* estou digitando.

**O que faria diferente:** configuraria a classe principal no `pom.xml` logo ao criar o projeto e testaria o `java -jar` cedo, não só no final. "BUILD SUCCESS" significa que compilou e os testes passaram, não que o artefato roda.

## Lições gerais

- Ler a mensagem de erro inteira: em todos os casos ela dizia exatamente o problema e a linha.
- Commits pequenos (8 no total, um por etapa) facilitaram ver o que tinha mudado quando algo quebrava (`git diff`).
- Pequenos erros de digitação (`mvn compil`, `git statys`, `mkdin`) também aconteceram. O terminal não adivinha: vale usar a tecla Tab para completar comandos.
