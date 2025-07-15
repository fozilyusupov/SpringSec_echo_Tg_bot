# SpringSec_echo_Tg_bot
# Spring Boot Telegram Messenger API

REST‑сервис на **Java 17 + Spring Boot 3**, который:

1. Регистрирует и аутентифицирует пользователей (JWT).
2. Генерирует личный токен для Telegram‑бота и привязывает `chatId`.
3. Принимает текстовые сообщения через REST и немедленно дублирует их пользователю в Telegram.
4. Хранит историю сообщений (дата + текст) и отдаёт её по API.

## 🔧 Стек

| Технология            | Версия |
|-----------------------|:------:|
| Java                  | 17     |
| Spring Boot           | 3.3.x  |
| Spring Security 6     | JWT    |
| Spring Data JPA       |        |
| PostgreSQL            |        |
| TelegramBots API      | 6.9.7  |
| Maven                 | 🛠️     |



## 📚 Функциональность

| Функция | Описание |
|--------|----------|
| 🔐 Регистрация | `POST /register` — регистрация с логином, паролем и именем |
| 🔑 Авторизация | `POST /login` — получение JWT токена |
| 🧾 Генерация токена | `GET /generate-token` — после авторизации |
| ✉️ Отправка сообщений | `POST /message` — отправка сообщения в API, пересылка в Telegram |
| 📜 История сообщений | `GET /messages` — список всех сообщений пользователя |
| 🤖 Telegram бот | Пользователь подписывается на бота и отправляет первое сообщение — для привязки chatId |

---

## 📦 Быстрый старт

```bash
# 1. Клонируем
git clone https://github.com/your-login/spring-telegram-bot-api.git
cd spring-telegram-bot-api

# 2. Создаём .env или экспортируем переменную TG_BOT_TOKEN
export TG_BOT_TOKEN=123456:AA...         # токен, полученный у @BotFather

# 3. Собираем и запускаем
./mvnw spring-boot:run
# или java -jar target/telegram-messenger-0.0.1-SNAPSHOT.jar



Регистрация
POST /api/auth/register
{
  "username": "username",
  "password": "123456",
  "name": "User"
}

логин
POST /api/auth/login
{
  "username": "гыуктфьу",
  "password": "123456"
}
→ { "access": "eyJhbGciOi..." }

Привязка Telegram
POST /api/bot/token
Authorization: Bearer <JWT "eyJhbGciOi...">
→ e5f97dd4-d203-...

Отправка сообщения
POST /api/messages/send
Authorization: Bearer  <JWT "eyJhbGciOi...">
"Привет, это тест!"

История сообщений
GET /api/messages
Authorization: Bearer  <JWT "eyJhbGciOi...">
[
  { "date": "2025‑07‑16T10:02:07Z", "body": "Привет!" }
]


