<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card class="shadow-soft">
            <b-card-header header-bg-variant="primary shadow-inset">
              <h4>Crear un decanato</h4>
            </b-card-header>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <b-row>
                <p></p>
                <b-card-body>
                  <b-row>
                    <b-col>
                      <b-form-group
                        id="grupo-nombre"
                        label="Nombre del decanato:"
                        label-for="nombre"
                        align="left"
                      >
                        <b-form-input
                          id="nombre"
                          v-model="estatus.estado"
                          required
                          placeholder="Ingrese el nombre del decanato"
                        ></b-form-input>
                      </b-form-group>
                    </b-col>
                  </b-row>
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
                      Crear
                      <b-icon icon="plus-square-fill"></b-icon>
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
  data() {
    return {
      estatus: {
        estado: ""
      },
      show: true
    };
  },
  methods: {
    async onSubmit(evt) {
      evt.preventDefault();
      var respuesta = await ServiciosAPI.guardarEstatus(this.decanato);
      console.log(respuesta);
      if (respuesta.status == 200) {
        this.$alert(
          "¡Se ha creado el estatus con éxito!",
          "Éxito",
          "success"
        ).then(click => {
          this.onReset(evt);
        });
      } else
        this.$alert("¡No se ha podido crear el estatus!", "Error", "error");
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.estatus.nombre = "";
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