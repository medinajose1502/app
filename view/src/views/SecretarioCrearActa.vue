<template>
  <div>
    <b-container>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card>
            <b-form @submit="onSubmit" @reset="onReset" v-if="show">
              <b-calendar
                v-model="value"
                v-bind="labels[locale] || {}"
                :locale="locale"
                :start-weekday="weekday"
                :hide-header="hideHeader"
                :show-decade-nav="showDecadeNav"
                @context="onContext"
              ></b-calendar>

              <b-form-group
                id="input-group-2"
                label="Fecha de la sesión"
                label-for="input-2"
                align="left"
              >
                <b-form-input
                  id="input-2"
                  v-model="formulario.fecha"
                  type="password"
                  required
                  placeholder="Ingrese su contraseña"
                ></b-form-input>
              </b-form-group>

              <b-form-group id="input-group-3" label="Descripción" label-for="input-3" align="left">
                <b-form-textarea
                  id="input-3"
                  v-model="formulario.descripcion"
                  type="text"
                  required
                  placeholder="Ingrese la descripción del acta"
                ></b-form-textarea>
              </b-form-group>

              <div align="right">
                <b-button type="reset" variant="danger">Limpiar</b-button>
                <b-button type="submit" variant="primary">Ingresar</b-button>
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
      acta: {
        tipo: "",
        descripcion: "",
        fecha: ""
      },
      value: "",
      context: null,
      showDecadeNav: true,
      hideHeader: false,
      locale: "en-ES",
      locales: [
        { value: "en-US", text: "English US (en-US)" },
        { value: "es-ES", text: "Español" }
      ],
      weekday: 0,
      weekdays: [
        { value: 0, text: "Domingo" },
        { value: 1, text: "Lunes" },
        { value: 2, text: "Martes" },
        { value: 3, text: "Miércoles" },
        { value: 4, text: "Jueves" },
        { value: 5, text: "Viernes" },
        { value: 6, text: "Sábado" }
      ],
      labels: {
        es: {
          labelPrevDecade: "Decada anterior",
          labelPrevYear: "Año pasado",
          labelPrevMonth: "Mes pasado",
          labelCurrentMonth: "Mes actual",
          labelNextMonth: "Proximo mes",
          labelNextYear: "Proximo año",
          labelNextDecade: "Proxima decada",
          labelToday: "Hoy",
          labelSelected: "Fecha seleccionada",
          labelNoDateSelected: "No ha elegido una fecha",
          labelCalendar: "Calendario",
          labelNav: "Navegación",
          labelHelp: "Use las teclas para cambiar la fecha"
        },
        show: true
      },
      methods: {
        onContext(ctx) {
          this.context = ctx;
        },
        onSubmit(evt) {
          evt.preventDefault();
          ServiciosAPI.registrarActa(this.acta);
        },
        onReset(evt) {
          evt.preventDefault();
          // Reset our form values
          this.acta.tipo = "";
          this.acta.descripcion = "";
          this.acta.fecha = "";
          // Trick to reset/clear native browser form validation state
          this.show = false;
          this.$nextTick(() => {
            this.show = true;
          });
        }
      }
    };
  }
};
</script>