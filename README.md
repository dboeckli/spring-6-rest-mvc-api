# Spring Framework 6: Beginner to Guru

This is a library project for spring-6-mvc-api

## Sandbox (local dev environment)

The sandbox is provisioned by the opencode-sandbox-kit and runs as a Docker container. It mounts this
repo, starts the agent, and connects the IntelliJ MCP server.

Allow the kit source (GitHub without cloning):

```powershell
sbx settings set kit.allowedSources --% "[\"docker.io/\",\"github.com/dboeckli/\"]"
```

Start a new sandbox:

```powershell
sbx run opencode `
    --name spring-6-rest-mvc-api `
    --static-mcp idea `
    -t docker/sandbox-templates:opencode-docker-0.5.0 `
    --kit "git+https://github.com/dboeckli/opencode-sandbox-kit.git#dir=opencode-agent" `
    "C:\development\projects\spring-6-rest-mvc-api" `
    "C:\development\maven-repo:ro"
```

Start the sandbox with Kubernetes support:

```powershell
sbx run opencode `
    --name spring-6-rest-mvc-api `
    --static-mcp idea `
    -t docker/sandbox-templates:opencode-docker-0.5.0 `
    --kit "git+https://github.com/dboeckli/opencode-sandbox-kit.git#dir=opencode-agent" `
    "C:\development\projects\spring-6-rest-mvc-api" `
    "C:\development\maven-repo:ro" `
    "$env:USERPROFILE\.kube:ro"
```

Claude Code (Home, against `api.anthropic.com`):

```powershell
sbx run claude `
    --name spring-6-rest-mvc-api `
    --static-mcp idea `
    -t docker/sandbox-templates:claude-code-docker-0.5.0 `
    --kit "git+https://github.com/dboeckli/opencode-sandbox-kit.git#dir=opencode-agent" `
    "C:\development\projects\spring-6-rest-mvc-api" `
    "C:\development\maven-repo:ro"
```

Mammouth Code (own agent kit; template pin is in the spec image, no `-t`):

```powershell
sbx run mammouth `
    --name spring-6-rest-mvc-api `
    --static-mcp idea `
    --kit "git+https://github.com/dboeckli/opencode-sandbox-kit.git#dir=mammouth-agent" `
    "C:\development\projects\spring-6-rest-mvc-api" `
    "C:\development\maven-repo:ro"
```

Apply the kit to an existing sandbox (restarts the sandbox, VM state is kept):

```powershell
sbx kit add spring-6-rest-mvc-api "git+https://github.com/dboeckli/opencode-sandbox-kit.git#dir=opencode-agent"
```

The `C:\development\maven-repo:ro` mount reuses the read-only host Maven cache. For GitHub Packages
resolution the kit proxy injects the `github-maven` credential — only real Maven builds are
representative (`mvn dependency:get` ignores settings-`<proxies>`).

## Getting Your Development Environment Setup

### Recommended Versions

|       Recommended       |                               Reference                                |                                                                                                         Notes                                                                                                          |
|-------------------------|------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Oracle Java 21 JDK      | [Download](https://www.oracle.com/java/technologies/downloads/#java21) | Java 17 or higher is required for Spring Framework 6. Java 21 is recommended for the course.                                                                                                                           |
| IntelliJ 2024 or Higher | [Download](https://www.jetbrains.com/idea/download/)                   | Ultimate Edition recommended. Students can get a free 120 trial license [here](https://github.com/springframeworkguru/spring5webapp/wiki/Which-IDE-to-Use%3F#how-do-i-get-the-free-120-day-trial-to-intellij-ultimate) |
| Maven 3.9.6 or higher   | [Download](https://maven.apache.org/download.cgi)                      | [Installation Instructions](https://maven.apache.org/install.html)                                                                                                                                                     |
| Gradle 8.7 or higher    | [Download](https://gradle.org/install/)                                |                                                                                                                                                                                                                        |
| Git 2.39 or higher      | [Download](https://git-scm.com/downloads)                              |                                                                                                                                                                                                                        |
| Git GUI Clients         | [Downloads](https://git-scm.com/downloads/guis)                        | Not required. But can be helpful if new to Git. SourceTree is a good option for Mac and Windows users.                                                                                                                 |

## All Spring Framework Guru Courses

### Spring Framework 6

* [Spring Framework 6 - Beginner to Guru](https://www.udemy.com/course/spring-framework-6-beginner-to-guru/?referralCode=2BD0B7B7B6B511D699A9)
* [Spring AI: Beginner to Guru](https://www.udemy.com/course/spring-ai-beginner-to-guru/?referralCode=EF8DB31C723FFC8E2751)
* [Hibernate and Spring Data JPA: Beginner to Guru](https://www.udemy.com/course/hibernate-and-spring-data-jpa-beginner-to-guru/?referralCode=251C4C865302C7B1BB8F)
* [API First Engineering with Spring Boot](https://www.udemy.com/course/api-first-engineering-with-spring-boot/?referralCode=C6DAEE7338215A2CF276)
* [Introduction to Kafka with Spring Boot](https://www.udemy.com/course/introduction-to-kafka-with-spring-boot/?referralCode=15118530CA63AD1AF16D)
* [Spring Security: Beginner to Guru](https://www.udemy.com/course/spring-security-core-beginner-to-guru/?referralCode=306F288EB78688C0F3BC)

