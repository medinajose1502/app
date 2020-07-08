<template>
  <b-card>
    <b-card-header header-bg-variant="primary" header-text-variant="white">
      <h3>Usuarios:</h3>
    </b-card-header>
    <p></p>
    <b-card-body>
      <b-row>
        <b-col class="my-1">
          <b-form-group
            label="Filtro"
            label-cols-sm="3"
            label-align-sm="left"
            label-size="sm"
            label-for="filtro"
            class="mb-0"
          >
            <b-input-group size="sm">
              <b-form-input
                v-model="filtro"
                type="search"
                id="filtro"
                placeholder="Escriba algo para buscar"
              ></b-form-input>
              <b-input-group-append>
                <b-button :disabled="!filtro" @click="filtro = ''">Filtrar</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>
      </b-row>
      <b-row>
        <b-table
          id="tabla-usuarios"
          hover
          :items="usu"
          :fields="fields"
          :filtro="filtro"
          :per-page="perPage"
          :current-page="currentPage"
          primary-key="id"
          @row-clicked="verUsuario"
        ></b-table>
      </b-row>
      <b-row>
        <b-col>
          <b-pagination
            align="right"
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="tabla-usuarios"
          ></b-pagination>
        </b-col>
      </b-row>
    </b-card-body>
    <b-card-footer footer-bg-variant="primary" align="right">
      <router-link :to="{ name: 'AdminCrearUsuario'}">
        <b-button variant="info">Crear usuario</b-button>
      </router-link>
    </b-card-footer>
  </b-card>
</template>

<script>
export default {
  props: {
    items: {
      type: Array
    }
  },
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "cedula",
          sortable: true
        },
        {
          key: "nombres",
          sortable: true
        },
        {
          key: "apellidos",
          sortable: true
        }
      ],
      filtro: ""
    };
  },

  computed: {
    rows() {
      return this.usu.length;
    },
    usu() {
      return this.filtro
        ? this.items.filter(
            item =>
              item.cedula.includes(this.filtro) ||
              item.nombres.includes(this.filtro) ||
              item.apellidos.includes(this.filtro)
          )
        : this.items;
    }
  },
  methods: {
    verUsuario(item) {
      this.$router.push("/admin/usuario/ver/" + parseInt(item.id, 10));
    }
  }
};
</script>

<style>
</style>