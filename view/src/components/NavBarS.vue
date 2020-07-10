<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="primary shadow-soft">
      <router-link :to="{ name: 'SecretarioHome'}">
        <b-navbar-brand class="primary text-secondary">Inicio</b-navbar-brand>
      </router-link>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <div v-show="$store.getters.nombreUsuarioSesion != ''">
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template v-slot:button-content>
                <em>{{$store.getters.nombreUsuarioSesion}}</em>
              </template>
              <b-dropdown-item @click="cerrarSesion">Cerrar Sesión</b-dropdown-item>
            </b-nav-item-dropdown>
          </div>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  data() {
    return {
      admin: false,
      secretario: false,
      sesion: false
    };
  },
  methods: {
    cerrarSesion(){
      this.$confirm("¿Esta seguro que desea cerrar sesión?","Cierre de sesión").then(()=>{this.$router.push("/logout")})
    }
  }
};
</script>

<style>
</style>