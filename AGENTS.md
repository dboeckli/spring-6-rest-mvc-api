# AGENTS.md

Reine Java-Library (Maven, `<packaging>jar</packaging>`) auf **Java 25** (Spring Boot Parent 4.1.1,
`spring-boot-starter-parent`). Package `ch.guru.springframework.spring6restmvcapi` — enthält nur DTOs
(`BeerDTO`, `CustomerDTO`, `BeerOrderDTO`, ...) und Events (`OrderPlacedEvent`, `DrinkRequestEvent`,
`DrinkPreparedEvent`); **kein** `@SpringBootApplication`, kein Docker/Helm/Kubernetes.
Wird von downstream-Apps (z. B. `spring-6-rest-mvc`) als Maven-Dependency konsumiert.

## Build & test commands

- Full build: `./mvnw clean install` — format checks (`validate`), unit tests, package.
- Unit tests only: `./mvnw test`. Single test: `./mvnw test -Dtest=BeerDTOTest`.
- Format check only: `./mvnw validate` (spring-javaformat + spotless).

After changing code, always verify: run the relevant Maven goal above and report its output
(evidence, not just "done").

## Sandbox build quirk (background)

This sandbox mounts the repo via filesystem passthrough, which blocks symlinks — Spotless's
`npm install` (prettier) would fail with `EPERM` unless npm skips bin links. The sandbox kit sets
`npm_config_bin_links=false` globally (`spec.yaml` → `environment.variables`), so no manual export
is needed here. On a normal host (Windows/CI) this does not apply either.

## Formatting is enforced (fails the `validate` phase)

- Java: Spring Java Format → fix with `./mvnw spring-javaformat:apply`.
- Everything else (pom.xml, `**/*.md`, json, `src/main/resources/application*.yaml`, `**/*.sh`):
  Spotless → fix with `./mvnw spotless:apply`.
- Spotless flexmark also formats markdown, so this file and any `.md` edits must stay flexmark-clean;
  run `./mvnw spotless:apply` after editing markdown.

## External dependency gotcha

- All DTO classes are plain records/POJOs validated with Hibernate Validator (JSR-380 annotations on
  fields) — no database, no web layer, no MapStruct in this module.
- Version bumps come via Renovate (Maven manager); keep the Spring Boot parent version and the
  java.version property in sync.
