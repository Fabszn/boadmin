import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path  from 'path';
import { viteMockServe } from 'vite-plugin-mock';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue(),
    viteMockServe({
    // default
    mockPath: './mock-api/'
})],
  resolve: {
    alias: {
      '~bootstrap': path.resolve(__dirname, 'node_modules/bootstrap'),
    }
  }
})


