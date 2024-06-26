/* eslint-disable */

module.exports = async function () {
  // Put clean up logic here (e.g. stopping services, docker-compose, etc.).
  // Hint: `globalThis` is shared between setup and teardown.
  console.log(globalThis.__TEARDOWN_MESSAGE__);

  console.log('Tearing down server');
  globalThis.__SERVER_PROCESS__.kill('SIGINT');

  console.log('Tearing down undici client');
  await globalThis.__UNDICI_RETRY_AGENT__.close();
  // await globalThis.__UNDICI_CLIENT__.close();
  console.log('Tearing down compose');
  await globalThis.__COMPOSE__.down(globalThis.__COMPOSE_CONFIG__);
};
