# Integration tests for Jakarta Base Rest

This e2e project is needed to test do smoke tests and do integration testing on
the single lib

Separating the e2e tests from the unit tests is a good practice and suggested by
NX (check nestjs scaffolds from nx)

This makes the test lib agnostic (can use jest) and provide any tooling needed

some examples:

- supertest
- jest / vitest
- docker-compose library
- testcontainers
- undici/ky/axios with retries

## main concept

it will spin up a docker compose with everything needed to run the app/lib (db,
seeding, etc) and then run the tests on it.

Ideally the cmd to start the app is already available or create custom test
setup

doesn't require package.json

must expose tasks test, e2e
