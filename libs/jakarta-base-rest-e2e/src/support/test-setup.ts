/* eslint-disable */

// import axios from 'axios';

import {
  // Agent,
  RetryAgent,
  Client,
  setGlobalDispatcher,
} from 'undici';

const undiciClient = new Client('http://localhost:8080', {
  keepAliveMaxTimeout: 10,
  keepAliveTimeout: 10,
});
const undiciRetryAgent = new RetryAgent(undiciClient);

setGlobalDispatcher(undiciClient);

globalThis.__UNDICI_CLIENT__ = undiciClient;
globalThis.__UNDICI_RETRY_AGENT__ = undiciRetryAgent;

// NOTE: how does this config affect axios in the tests? it's not imported in the tests.
// is some kind of global axios instance being used in the tests?
module.exports = async function () {
  // Configure axios for tests to use.
  // const host = process.env.HOST ?? 'localhost';
  // const port = process.env.PORT ?? '3000';
  // axios.defaults.baseURL = `http://${host}:${port}`;
};
