<template>
  <div class="form-floating">
    <input
      type="email"
      v-model="login"
      class="form-control"
      id="floatingInput"
      placeholder="name@example.com"
    />
    <label for="floatingInput">Email address</label>
  </div>
  <div class="form-floating">
    <input
    v-model="password"
      type="password"
      class="form-control"
      id="floatingPassword"
      placeholder="Password"
    />
    <label for="floatingPassword">Password</label>
  </div>
  <button class="w-100 btn btn-lg btn-primary"  v-on:click="submit">Sign in</button>
</template>

<script setup lang="ts">

var login:string="";
var password:string=""


function submit() {
  fetch("/api/login", {
          body: JSON.stringify({
            login: login,
            mdp: password,
          }),
          method: "POST",
        })
}





</script>

<style scoped>
</style>