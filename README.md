# 🚀 Deployment & Infrastructure

> AWS EC2 환경에서 Docker 기반으로 Spring Boot 애플리케이션을 배포하고, Nginx Reverse Proxy 및 HTTPS를 적용하였다. <br>  GitHub Actions를 이용하여 자동 배포되는 CD 환경을 구축하였다.

---

## CD Pipeline

```text
[IntelliJ]
     │
 git push
     ▼
[GitHub]
     │
     ▼
[GitHub Actions]
     │
 Build & Deploy
     ▼
[AWS EC2]
     │
Docker Compose
     ▼
Spring Boot
```

---

## 📦 Deployment Stack

| Category | Technology |
|----------|------------|
| Application | Spring Boot |
| Server | AWS EC2 |
| Container | Docker |
| Orchestration | Docker Compose |
| Reverse Proxy | Nginx |
| HTTPS | Let's Encrypt |
| Database | MySQL |
| CI/CD | GitHub Actions |

---
