# 💸 API de Transferências — PicPay Simplificado

Esta é uma **API REST de transferências financeiras** desenvolvida em **Java com Spring Boot**, como forma de estudo seguindo passos do canal **Javanauta**.

O projeto foi criado como solução para o **Desafio Back-end PicPay (PicPay Simplificado)**, respeitando todas as regras de negócio propostas.

A única diferença em relação à referência do Javanauta é que a **população inicial do banco** foi feita via **`data.sql`**, em vez de criar classes Java para inserir os dados.

---

## 🎯 Objetivo do Projeto

Implementar uma versão **simplificada do PicPay**, onde:

* Usuários possuem **carteiras com saldo**
* É possível **transferir valores entre usuários**
* Existem **dois tipos de usuário**:

  * **COMUM** → pode enviar e receber
  * **LOJISTA** → apenas recebe
* Toda transferência é **transacional**
* A operação consulta:

  * Um **serviço autorizador externo**
  * Um **serviço de notificação externo**

---

## 🚀 Funcionalidades

* Cadastro de usuários com **CPF/CNPJ e e-mail únicos**
* Transferência de valores entre usuários
* Validação de saldo antes da transferência
* Bloqueio de transferências realizadas por **lojistas**
* Consulta a **serviço autorizador externo**
* Envio de **notificação externa** após transferência
* Persistência das transações realizadas
* Operações protegidas por **transação atômica (`@Transactional`)**

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21+
* **Framework:** Spring Boot
* **Persistência:** Spring Data JPA (Hibernate)
* **Banco de Dados:** PostgreSQL
* **Cliente HTTP:** OpenFeign
* **Gerenciamento de Transações:** Spring Transaction
* **Banco inicializado via:** `data.sql` (diferença em relação ao vídeo do Javanauta)
* **Build:** Maven

---

## 🧱 Modelagem Principal

* **Usuario**

  * id
  * nomeCompleto
  * email (único)
  * cpfCnpj (único)
  * senha
  * tipoUsuario (COMUM | LOJISTA)
* **Carteira**

  * saldo
  * vínculo 1–1 com usuário
* **Transacao**

  * valor
  * dataHoraTransacao
  * pagador
  * recebedor

---

## 🔗 Endpoint de Transferência

### Realizar transferência

**POST** `/transfer`

```json
{
  "value": 100.0,
  "payer": 1,
  "payee": 3
}
```

### Regras aplicadas no fluxo:

1. Verifica se o pagador existe
2. Verifica se o recebedor existe
3. Impede transferências feitas por **lojistas**
4. Valida saldo disponível
5. Consulta serviço autorizador externo
6. Atualiza saldo das carteiras
7. Registra a transação
8. Envia notificação externa

Caso qualquer etapa falhe, **toda a operação é revertida**.

---

## 🌐 Serviços Externos Utilizados (Mock)

* **Autorização da transferência**

  * `GET https://util.devi.tools/api/v2/authorize`

* **Envio de notificação**

  * `POST https://util.devi.tools/api/v1/notify`

---

## ⚙️ Inicialização do Banco de Dados

Ao contrário do vídeo do **Javanauta**, onde os dados são populados via código Java, neste projeto foi utilizado:

* 📄 **`data.sql`** para:

  * Popular usuários
  * Criar carteiras iniciais
  * Facilitar testes locais
* 🔄 `spring.jpa.hibernate.ddl-auto=update`

Essa abordagem mantém o fluxo igual ao vídeo, mas simplifica a execução local.

---

## ▶️ Como Executar o Projeto

1. Configure um banco **PostgreSQL**
2. Ajuste as credenciais no `application.properties`
3. Execute a aplicação:

```bash
mvn spring-boot:run
```

4. A API estará disponível em:

```
http://localhost:8080
```

---

## 📝 Observações

* O foco do projeto é **o fluxo de transferência**
* Não foram implementados:

  * Autenticação
  * Frontend
* O projeto prioriza:

  * Código limpo
  * Separação de responsabilidades
  * Clareza na regra de negócio

---

## 📚 Referências

* 📌 **Desafio Back-end PicPay**
* 🎥 Vídeo base: Canal **Javanauta**

