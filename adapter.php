<?php
class ChargerAdapter implements UsbCharger {
    private TypeCCharger typeCCharger;

    ChargerAdapter(TypeCCharger typeCCharger) {
        this.typeCCharger = typeCCharger;
    }

    @Override
    public void chargeWithUsb() {
        typeCCharger.chargeWithTypeC();
    }
}