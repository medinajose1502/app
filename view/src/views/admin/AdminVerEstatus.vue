<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h3>Ver estatus</h3>
            </b-card-header>
            <b-form text-variant="black" align="left">
              <b-card-body>
                <p></p>
                <label for="estado">Descripción del estado:</label>
                <b-form-textarea v-model="estatus.estado" id="estado" readonly></b-form-textarea>
                <p></p>
              </b-card-body>

              <b-card-footer footer-bg-variant="primary">
                <b-row>
                  <b-col>
                    <router-link :to="{ name: 'AdminHome'}">
                      <b-button align="left" variant="primary text-info">
                        Volver a inicio
                        <b-icon icon="house-fill"></b-icon>
                      </b-button>
                    </router-link>
                  </b-col>
                  <b-col align="right">
                    <b-button type="submit" @click="eliminarEstatus" variant="primary text-danger">
                      Eliminar
                      <b-icon icon="x-square-fill"></b-icon>
                    </b-button>
                    <router-link :to="{ name: 'AdminEditarEstatus', params: {id: this.id} }">
                      <b-button type="submit" variant="primary text-info">
                        Editar
                        <b-icon icon="brush"></b-icon>
                      </b-button>
                    </router-link>
                  </b-col>
                </b-row>
              </b-card-footer>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
      <p></p>
      <p></p>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <!--<TablaActas :items="estatus.actas" />-->
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
      estatus: {
        id: "",
        estado: "",
        actas: []
      }
    };
  },
  created() {
    ServiciosAPI.getEstatus(this.id)
      .then(response => {
        this.estatus = response.data;
      })
      .catch(error => {
        console.log("Ocurrió un error: " + error.response);
      });
  },
  methods: {
    async eliminarEstatus() {
      var respuesta = await ServiciosAPI.eliminarEstatus(this.id);
      if (respuesta.status == 200) {
        this.$confirm(
          "¿De verdad está seguro de querer eliminar el estatus? No podrá recuperar los datos del estatus, ni aquellos asociados.",
          "¿Eliminar?",
          "warning"
        ).then(click => {
          this.$router.push("/admin");
        });
      } else this.$alert("¡No se pudo eliminar el estatus!", "Error", "error");
    }
  }
};
</script>

<style>
</style>