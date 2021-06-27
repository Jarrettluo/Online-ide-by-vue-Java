<template>
  <div class="home">
    <!-- <img alt="Vue logo" src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/> -->
    <CodePanel/>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import HelloWorld from '@/components/HelloWorld.vue'; // @ is an alias to /src
import CodePanel from './codePanel.vue'
export default Vue.extend({
  name: 'home',
  components: {
    HelloWorld,
    CodePanel
  },
});
</script>
