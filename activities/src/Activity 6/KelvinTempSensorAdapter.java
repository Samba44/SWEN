public class KelvinTempSensorAdapter implements ITempSensor {
   KelvinTempSensor kts;
   
   public KelvinTempSensorAdapter(KelvinTempSensor new_kts) {
      kts = new_kts;
   }
   
   public int temp() {
      return kts.reading();
   }
}