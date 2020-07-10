<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h3>Ver usuario</h3>
            </b-card-header>

            <b-card-body>
              <b-row>
                <b-col>
                  <label for="cedula">Cédula:</label>
                  <b-form-input v-model="usuario.cedula" id="cedula" readonly></b-form-input>
                  <p></p>
                </b-col>

                <b-col>
                  <label for="rol">Rol del usuario</label>
                  <b-form-input v-model="usuario.roles[0].tipo" id="rol" readonly></b-form-input>
                  <p></p>
                </b-col>
              </b-row>
              <p></p>

              <b-row>
                <b-col>
                  <label for="nombres">Nombres:</label>
                  <b-form-input v-model="usuario.nombres" id="nombres" readonly></b-form-input>
                  <p></p>
                </b-col>

                <b-col>
                  <label for="apellidos">Apellidos</label>
                  <b-form-input v-model="usuario.apellidos" id="apellidos" readonly></b-form-input>
                  <p></p>
                </b-col>
              </b-row>
            </b-card-body>
            <b-card-footer footer-bg-variant="primary">
              <b-row>
                <b-col align="left">
                  <router-link :to="{ name: 'AdminHome'}">
                    <b-button variant="primary text-info">
                      Volver a inicio
                      <b-icon icon="house-fill"></b-icon>
                    </b-button>
                  </router-link>
                </b-col>
                <b-col align="right">
                  <b-button type="submit" @click="eliminarUsuario" variant="primary text-danger">
                    Eliminar
                    <b-icon icon="x-square-fill"></b-icon>
                  </b-button>
                  <router-link :to="{ name: 'AdminEditarUsuario', params: {id: this.id} }">
                    <b-button type="submit" variant="primary text-info">
                      Editar
                      <b-icon icon="brush"></b-icon>
                    </b-button>
                  </router-link>
                </b-col>
              </b-row>
            </b-card-footer>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
      <p></p>
      <p></p>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <TablaActas :items="usuario.actas" />
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";
import TablaActas from "@/components/TablaActas.vue";
export default {
  components: {
    TablaActas
  },
  props: {
    id: {
      type: Number
    }
  },
  data() {
    return {
      usuario: {
        id: null,
        cedula: "",
        nombres: "",
        apellidos: "",
        roles: [],
        actas: []
      }
    };
  },
  created() {
    ServiciosAPI.getUsuarioId(this.id)
      .then(response => {
        this.usuario = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  },
  methods: {
    async eliminarUsuario() {
      var respuesta = await ServiciosAPI.eliminarUsuario(this.id);
      if (respuesta.status == 200) {
        this.$confirm(
          "¿De verdad está seguro de querer eliminar el usuario? No podrán recuperar los datos del usuario, ni aquellos asociados.",
          "¿Eliminar?",
          "warning"
        ).then(click => {
          this.$router.push("/admin");
        });
      } else this.$alert("¡No se pudo eliminar el usuario!", "Error", "error");
    }
  }
};
</script>

<style>
</style>