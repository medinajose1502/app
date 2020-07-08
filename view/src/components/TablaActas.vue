<template>
  <b-card>
    <b-card-header header-bg-variant="primary" header-text-variant="white">
      <h3>Decanatos:</h3>
    </b-card-header>
    <p></p>
    <b-card-body>
      <b-row>
        <b-col lg="6" class="my-1">
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
                v-model="filter"
                type="search"
                id="filtro"
                placeholder="Escriba algo para buscar"
              ></b-form-input>
              <b-input-group-append>
                <b-button :disabled="!filter" @click="filter = ''">Filtrar</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>
      </b-row>
      <b-row>
        <b-table
          id="tabla-actas"
          hover
          :items="items"
          :fields="fields"
          primary-key="id"
          @row-clicked="verDecanato"
        ></b-table>
      </b-row>
      <b-row>
        <b-pagination
          align="right"
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="tabla-actas"
        ></b-pagination>
      </b-row>
    </b-card-body>
    <b-card-footer>
      <router-link :to="{ name: 'AdminCrearDecanato'}">
        <b-button variant="info">Crear decanato</b-button>
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
          key: "nombre",
          sortable: true
        },
        {
          key: "ubicacion",
          label: "Ubicación",
          sortable: false
        }
      ]
    };
  },

  computed: {
    rows() {
      return this.items.length;
    }
  },
  methods: {
    verDecanato(index) {}
  }
};
</script>

<style>
</style>