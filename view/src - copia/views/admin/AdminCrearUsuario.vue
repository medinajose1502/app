<template>
  <div>
    <NavBarA />
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-card>
            <b-card-header header-bg-variant="primary" header-text-variant="white">
              <h3>Crear usuario</h3>
            </b-card-header>
            <b-card-body>
              <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                <p></p>
                <b-row>
                  <b-col>
                    <b-form-group
                      id="input-group-1"
                      label="Cédula"
                      label-for="input-1"
                      align="left"
                    >
                      <b-form-input
                        id="input-1"
                        v-model="usuario.cedula"
                        required
                        placeholder="Ingrese la cédula del usuario."
                      ></b-form-input>
                    </b-form-group>
                  </b-col>
                  <b-col>
                    <b-form-group
                      id="input-group-2"
                      label="Contraseña"
                      label-for="input-2"
                      align="left"
                    >
                      <b-form-input
                        id="input-2"
                        v-model="usuario.contrasenna"
                        required
                        type="password"
                        placeholder="Ingrese la cédula del usuario."
                      ></b-form-input>
                    </b-form-group>
                  </b-col>
                </b-row>
                <b-row>
                  <b-col>
                    <b-form-group
                      id="input-group-3"
                      label="Nombres"
                      label-for="input-3"
                      align="left"
                    >
                      <b-form-input
                        id="input-3"
                        v-model="usuario.nombres"
                        required
                        placeholder="Ingrese los nombres del usuario."
                      ></b-form-input>
                    </b-form-group>
                  </b-col>
                  <b-col>
                    <b-form-group
                      id="input-group-4"
                      label="Apellidos"
                      label-for="input-4"
                      align="left"
                    >
                      <b-form-input
                        id="input-4"
                        v-model="usuario.apellidos"
                        required
                        placeholder="Ingrese los apellidos del usuario."
                      ></b-form-input>
                    </b-form-group>
                  </b-col>
                </b-row>
                <p></p>

                <b-row>
                  <b-col>
                    <b-form-group
                      id="input-group-3"
                      label="Decanato"
                      label-for="decanato"
                      align="left"
                    >
                      <b-form-select
                        id="decanato"
                        v-model="usuario.decanato.id"
                        :options="opc"
                        class="mb-3"
                      ></b-form-select>
                    </b-form-group>
                  </b-col>
                  <b-col>
                    <b-form-group
                      id="input-group-3"
                      label="Rol de usuario"
                      label-for="tipo"
                      align="left"
                    >
                      <b-form-select id="tipo" v-model="rol.tipo" class="mb-3">
                        <b-form-select-option value="ROLE_ADMIN">Admin</b-form-select-option>
                        <b-form-select-option value="ROLE_SECRETARIO">Secretario</b-form-select-option>
                      </b-form-select>
                    </b-form-group>
                  </b-col>
                </b-row>
                <p></p>
                <b-card-footer footer-bg-variant="primary">
                  <b-row align="left">
                    <b-col>
                      <router-link :to="{ name: 'AdminHome'}">
                        <b-button variant="info">
                          Volver a inicio
                          <b-icon icon="house-fill"></b-icon>
                        </b-button>
                      </router-link>
                    </b-col>
                    <b-col align="right">
                      <b-button type="reset" variant="danger">
                        Limpiar formulario
                        <b-icon icon="trash-fill"></b-icon>
                      </b-button>
                      <b-button type="submit" variant="success">
                        Crear
                        <b-icon icon="plus-square-fill"></b-icon>
                      </b-button>
                    </b-col>
                  </b-row>
                </b-card-footer>
              </b-form>
            </b-card-body>
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
      decanatos: [],
      rol: {
        id: "",
        tipo: ""
      },
      usuario: {
        cedula: "",
        contrasenna: "",
        nombres: "",
        apellidos: "",
        roles: [],
        decanato: {
          id: ""
        }
      },
      show: true,
      opc: []
    };
  },
  async created() {
    var dec = await ServiciosAPI.getDecanatos();
    {
      if (dec.status == 200) this.decanatos = dec.data;
      this.crearOpciones();
    }
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      this.usuario.roles.push(this.rol);
      ServiciosAPI.guardarUsuario(this.usuario);
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.usuario.cedula = "";
      this.usuario.contrasenna = "";
      this.usuario.nombres = "";
      this.usuario.apellidos = "";
      this.usuario.roles = [];
      this.usuario.decanato.id = "";
      this.rol.id = "";
      this.rol.tipo = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    crearOpciones() {
      this.decanatos.forEach(decanato => {
        var op = { value: decanato.id, text: decanato.nombre };
        this.opc.push(op);
      });
    }
  }
};
</script>

<style>
</style>