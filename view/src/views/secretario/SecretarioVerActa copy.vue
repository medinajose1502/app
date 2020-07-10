<template>
  <div>
    <NavBarS />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h3>Ver acta</h3>
            </b-card-header>
            <b-card-body>
              <b-row>
                <b-col>
                  <label for="id">Número de la acta:</label>
                  <b-form-input v-model="acta.id" id="id" readonly></b-form-input>
                </b-col>
                <b-col>
                  <label for="tipo">Tipo de sesión:</label>
                  <b-form-input v-model="acta.tipo" id="tipo" readonly></b-form-input>
                </b-col>
                <b-col>
                  <label for="fecha">Fecha de la sesión:</label>
                  <b-form-input v-model="acta.fecha" id="fecha" readonly></b-form-input>
                </b-col>
              </b-row>
              <p></p>
              <b-row>
                <label for="descripcion">Descripción de la acta:</label>
                <b-form-textarea v-model="acta.descripcion" id="descripcion" readonly></b-form-textarea>
              </b-row>
              <p></p>
              <b-row>
                <b-col cols="12">
                  <a :href="enlace" download>
                    <b-button variant="primary text-success">
                      Descargar acta
                      <b-icon icon="arrow-down-square-fill"></b-icon>
                    </b-button>
                  </a>
                </b-col>
              </b-row>
            </b-card-body>
            <b-card-footer footer-bg-variant="primary">
              <b-row>
                <b-col align="left">
                  <router-link :to="{ name: 'SecretarioHome'}">
                    <b-button variant="primary text-info">
                      Volver a inicio
                      <b-icon icon="house-fill"></b-icon>
                    </b-button>
                  </router-link>
                </b-col>
                <b-col align="right">
                  <b-button type="submit" @click="eliminarActa" variant="primary text-danger">
                    Eliminar
                    <b-icon icon="x-square-fill"></b-icon>
                  </b-button>
                  <router-link :to="{ name: 'SecretarioEditarActa', params: {id: this.id} }">
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
    </b-container>
  </div>
</template>

<script>
import ServiciosAPI from "@/services/ServiciosAPI.js";

export default {
  props: {
    id: {
      type: Number
    }
  },
  data() {
    return {
      acta: {
        id: "",
        tipo: "",
        fecha: "",
        descripcion: ""
      }
    };
  },
  async created() {
    var act = await ServiciosAPI.getActa(this.id);
    consola.log(act.data);
    if (act.status == 200) {
      this.acta = act.data;
    }
  },
  methods: {
    eliminarActa: async function() {
      var respuesta = await ServiciosAPI.eliminarActa(this.id);
      if (respuesta.status == 200) this.$router.push("/secretario");
    }
  },
  computed: {
    enlace: function() {
      return "http://localhost:8080/acta/descargar/" + this.acta.id;
    }
  }
};
</script>

<style>
</style>