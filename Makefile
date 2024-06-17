show-tasks:
	@nx show project application --web
show-dep-graph:
	@nx dep-graph
show-graph:
	@nx graph
build-applicaton:
	@nx run application:build
build-all:
	@nx run-many --target=build
test-affected:
	@nx affected -t test
generate-ci-workflow:
	@npx nx generate ci-workflow --ci=github
