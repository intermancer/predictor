package com.intermancer.predictor.data;

/**
 * A ConsumeResponse is generated by every QuantumConsumer. Currently, the
 * ConsumeResponse simply tracks the hunger, or lack thereof, of the composite
 * QuantumConsumer.
 * 
 * @author johnfryar
 * 
 */
public class ConsumeResponse {

        public static final int CONSUME_COMPLETE_STATUS_VALUE = 0;
        public static final ConsumeResponse CONSUME_COMPLETE = new ConsumeResponse(
                        CONSUME_COMPLETE_STATUS_VALUE);

        public static final int HUNGRY_STATUS_VALUE = 1;

        private int status;
        private int hungryCyclesFollowing;

        public ConsumeResponse() {
        }

        public ConsumeResponse(int status) {
                this();
                this.status = status;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        @Override
        public boolean equals(Object obj) {
                if (obj instanceof ConsumeResponse) {
                        ConsumeResponse other = (ConsumeResponse) obj;
                        return (this.status == other.status)
                                        && (this.hungryCyclesFollowing == other.hungryCyclesFollowing);
                }
                return false;
        }

        @Override
        public int hashCode() {
                return status + (10 * hungryCyclesFollowing);
        }

        public static ConsumeResponse getHungryResponse(int period) {
                ConsumeResponse response = new ConsumeResponse(HUNGRY_STATUS_VALUE);
                response.setHungryCyclesFollowing(period);
                return response;
        }

        private void setHungryCyclesFollowing(int hungryCyclesFollowing) {
                this.hungryCyclesFollowing = hungryCyclesFollowing;
        }

        public int getHungryCyclesFollowing() {
                return hungryCyclesFollowing;
        }

        @Override
        public String toString() {
                return Integer.toString(status) + "|" + Integer.toString(hungryCyclesFollowing);
        }

}