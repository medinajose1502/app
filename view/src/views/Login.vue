<template>
  <div>
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <b-form-group id="input-group-1" label="Cédula" label-for="input-1" align="left">
                <b-form-input
                  id="input-1"
                  v-model="formulario.username"
                  type="text"
                  required
                  placeholder="Ingrese su cédula"
                ></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-2" label="Contraseña" label-for="input-2" align="left">
                <b-form-input
                  id="input-2"
                  v-model="formulario.password"
                  type="password"
                  required
                  placeholder="Ingrese su contraseña"
                ></b-form-input>
              </b-form-group>
              <p v-show="error">Las credenciales son incorrectas, vuelva a intentarlo.</p>
              <div align="right">
                <b-button type="reset" variant="primary text-danger">Limpiar</b-button>
                <b-button type="submit" variant="primary text-success">Ingresar</b-button>
              </div>
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
      formulario: {
        username: "",
        password: ""
      },
      show: true,
      error: false
    };
  },
  methods: {
    async onSubmit(evt) {
      evt.preventDefault();
      this.error = false;
      var respuesta = await ServiciosAPI.login(this.formulario);
      console.log(respuesta);
      console.log(respuesta.data);
      console.log(respuesta.status);
      if (respuesta.status == 200) {
        var usuario = await ServiciosAPI.getUsuarioSesion();
        await this.$store.dispatch("actualizarUsuarioSesion", usuario.data);
        console.log(this.$store.getters.rolUsuarioSesion);
        if (this.$store.getters.rolUsuarioSesion == "ROLE_ADMIN")
          this.$router.push("/admin");
        else this.$router.push("/secretario");
      } else this.$alert("Hello Vue Simple Alert.");
    },

    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.formulario.username = "";
      this.formulario.password = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>