<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h4>Editar un Estatus</h4>
            </b-card-header>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <b-row>
                <p></p>
                <b-card-body>
                  <b-row>
                    <b-col>
                      <b-form-input v-model="estatus.id" id="id" hidden readonly></b-form-input>
                      <b-form-group
                        id="grupo-estado"
                        label="estado del estatus:"
                        label-for="estado"
                        align="center"
                      >
                        <b-form-input
                          id="estado"
                          v-model="estatus.estado"
                          required
                          placeholder="Ingrese el estado del estatus"
                        ></b-form-input>
                      </b-form-group>
                    </b-col>
                  </b-row>

                  <p></p>
                  

                  <p></p>
                </b-card-body>
              </b-row>
              <p></p>

              <b-card-footer footer-bg-variant="primary">
                <b-row align="left">
                  <b-col>
                    <router-link :to="{ name: 'AdminHome'}">
                      <b-button variant="primary text-info">
                        Volver a inicio
                        <b-icon icon="house-fill"></b-icon>
                      </b-button>
                    </router-link>
                  </b-col>
                  <b-col align="right">
                    <b-button type="reset" variant="primary text-danger">
                      Limpiar formulario
                      <b-icon icon="trash-fill"></b-icon>
                    </b-button>
                    <b-button type="submit" variant="primary text-success">
                      Editar
                      <b-icon icon="brush"></b-icon>
                    </b-button>
                  </b-col>
                </b-row>
              </b-card-footer>
            </b-form>
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
      estatus: {
        id: "",
        estado: ""
      },
      show: true
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
    async onSubmit(evt) {
      evt.preventDefault();
      var respuesta = await ServiciosAPI.guardarEstatus(this.estatus);
      if (respuesta.status == 200)
        this.$alert(
          "¡Se ha editado el Estatus con éxito!",
          "Éxito",
          "success"
        ).then(click => {
          this.$router.push({
            name: "AdminVerEstatus",
            params: { id: this.id }
          });
        });
      else
        this.$alert("¡No se ha podido editar el Estatus!", "Error", "error");
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.estatus.estado = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>

<style>
</style>