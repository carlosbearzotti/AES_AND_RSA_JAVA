# Desafio Backend - Criptografia

Este é um projeto Spring Boot que demonstra a implementação de criptografia transparente usando JPA Attribute Converters.

## Como instalar o projeto

1. **Pré-requisitos:**
   - Java 17 ou superior.
   - Maven.

2. **Clone ou baixe o repositório.**
   O projeto já contém os wrappers do Maven (`mvnw`), então você não precisa ter o Maven instalado globalmente.

3. **Baixe as dependências:**
   Abra o terminal na pasta raiz do projeto e execute:
   ```bash
   ./mvnw clean install -DskipTests
   ```
   (No Windows: `mvnw.cmd clean install -DskipTests`)

## Como executar o projeto

Você pode executar o projeto de forma simples, pois ele utiliza um banco de dados H2 em memória. As chaves de criptografia são definidas como variáveis de ambiente, mas para facilitar o teste local, o `application.properties` já possui um *fallback* com chaves padrão de desenvolvimento.

Para executar via Maven:
```bash
./mvnw spring-boot:run
```
(No Windows: `mvnw.cmd spring-boot:run`)

### Executando com suas próprias variáveis de ambiente (Produção)
Se desejar passar suas próprias chaves (o recomendado), execute:
```bash
export AES_KEY="sua-chave-aes"
export RSA_PUBLIC_KEY="-----BEGIN PUBLIC KEY----- ... -----END PUBLIC KEY-----"
export RSA_PRIVATE_KEY="-----BEGIN PRIVATE KEY----- ... -----END PRIVATE KEY-----"
./mvnw spring-boot:run
```

## Como utilizar o projeto

A API REST estará rodando em `http://localhost:8080/api/transactions`.
O banco de dados H2 poderá ser acessado em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, user: `sa`, password em branco).

### Exemplo de Requisição (Criar Transação)

```bash
curl -X POST http://localhost:8080/api/transactions \
-H "Content-Type: application/json" \
-d '{
    "userDocument": "12345678909",
    "creditCardToken": "token-cartao-123",
    "value": 1500
}'
```

Se você consultar o banco de dados H2 após essa inserção, notará que os campos `user_document` e `credit_card_token` estarão em base64 e não em texto claro, confirmando a eficácia da criptografia transparente.

Ao fazer um GET na API:
```bash
curl http://localhost:8080/api/transactions
```
Você verá os dados em texto claro novamente, pois a camada de conversão (JPA Converter) descriptografou antes de chegar ao controller.
