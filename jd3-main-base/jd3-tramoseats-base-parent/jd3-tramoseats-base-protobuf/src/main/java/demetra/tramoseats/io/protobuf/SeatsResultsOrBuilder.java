// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tramoseats.proto

package demetra.tramoseats.io.protobuf;

public interface SeatsResultsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tramoseats.SeatsResults)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.modelling.ArimaModel seats_arima = 1;</code>
   * @return Whether the seatsArima field is set.
   */
  boolean hasSeatsArima();
  /**
   * <code>.modelling.ArimaModel seats_arima = 1;</code>
   * @return The seatsArima.
   */
  demetra.modelling.io.protobuf.ModellingProtos.ArimaModel getSeatsArima();
  /**
   * <code>.modelling.ArimaModel seats_arima = 1;</code>
   */
  demetra.modelling.io.protobuf.ModellingProtos.ArimaModelOrBuilder getSeatsArimaOrBuilder();

  /**
   * <code>.modelling.SarimaModel seats_sarima = 2;</code>
   * @return Whether the seatsSarima field is set.
   */
  boolean hasSeatsSarima();
  /**
   * <code>.modelling.SarimaModel seats_sarima = 2;</code>
   * @return The seatsSarima.
   */
  demetra.modelling.io.protobuf.ModellingProtos.SarimaModel getSeatsSarima();
  /**
   * <code>.modelling.SarimaModel seats_sarima = 2;</code>
   */
  demetra.modelling.io.protobuf.ModellingProtos.SarimaModelOrBuilder getSeatsSarimaOrBuilder();

  /**
   * <code>bool mean = 3;</code>
   * @return The mean.
   */
  boolean getMean();

  /**
   * <code>.modelling.UcarimaModel canonical_decomposition = 4;</code>
   * @return Whether the canonicalDecomposition field is set.
   */
  boolean hasCanonicalDecomposition();
  /**
   * <code>.modelling.UcarimaModel canonical_decomposition = 4;</code>
   * @return The canonicalDecomposition.
   */
  demetra.modelling.io.protobuf.ModellingProtos.UcarimaModel getCanonicalDecomposition();
  /**
   * <code>.modelling.UcarimaModel canonical_decomposition = 4;</code>
   */
  demetra.modelling.io.protobuf.ModellingProtos.UcarimaModelOrBuilder getCanonicalDecompositionOrBuilder();

  /**
   * <code>.sa.SaDecomposition stochastics = 5;</code>
   * @return Whether the stochastics field is set.
   */
  boolean hasStochastics();
  /**
   * <code>.sa.SaDecomposition stochastics = 5;</code>
   * @return The stochastics.
   */
  demetra.sa.io.protobuf.SaProtos.SaDecomposition getStochastics();
  /**
   * <code>.sa.SaDecomposition stochastics = 5;</code>
   */
  demetra.sa.io.protobuf.SaProtos.SaDecompositionOrBuilder getStochasticsOrBuilder();

  public demetra.tramoseats.io.protobuf.SeatsResults.ModelCase getModelCase();
}
