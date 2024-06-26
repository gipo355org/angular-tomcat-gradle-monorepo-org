import { HttpStatus as H } from '@nestjs/common';
// import { Client } from 'undici';
// import axios from 'axios';

describe('APP GET /healthz', () => {
  const u11 = globalThis.__UNDICI_CLIENT__;
  // let undiciClient: Client;
  // beforeAll(() => {
  //   undiciClient = new Client('http://localhost:3000', {
  //     keepAliveMaxTimeout: 10,
  //     keepAliveTimeout: 10,
  //   });
  // });
  it('Server should be up', async () => {
    const { body, statusCode } = await u11.request({
      method: 'GET',
      path: '/healthz',
    });

    // only needed if using streams (read(), for await of)
    // body.setEncoding('utf8');

    // const data = JSON.parse(await body.read());
    const data = await body.json();
    console.log(data);

    expect(statusCode).toBe(H.OK);
    expect(data).toEqual({ ok: true });
  });
});
