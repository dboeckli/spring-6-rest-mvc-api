# Spring Framework 6: Beginner to Guru

This is the shared library module for the Spring Framework 6 - Beginner to Guru course. It provides
the DTOs (`BeerDTO`, `CustomerDTO`, `BeerOrderDTO`, `BeerOrderLineDTO`, ...) and events
(`OrderPlacedEvent`, `DrinkRequestEvent`, `DrinkPreparedEvent`) in the package
`ch.guru.springframework.spring6restmvcapi`. The downstream Spring Boot app
[`dboeckli/spring-6-rest-mvc`](https://github.com/dboeckli/spring-6-rest-mvc) consumes this jar as a
Maven dependency from GitHub Packages.

The module is built on Java 25 with Maven (`./mvnw`). Build and test commands, the required toolchain,
and the enforced formatting (spring-javaformat + spotless) are documented in
[`AGENTS.md`](AGENTS.md).

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

Apply the kit to an existing sandbox (restarts the sandbox, VM state is kept):

```powershell
sbx kit add spring-6-rest-mvc-api "git+https://github.com/dboeckli/opencode-sandbox-kit.git#dir=opencode-agent"
```

