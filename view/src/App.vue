<template>
  <div>
    <div id="app">
      <router-view />
      <Footer />
    </div>
  </div>
</template>

<style>

#app {

  text-align: center;
  color: #2c3e50;
  height: 100%;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
 
}


.btn{
  margin-left: 1em;
}

.filtrico {
    margin-bottom: 1em;
}
</style>
