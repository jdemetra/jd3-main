// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: x13.proto

package demetra.x13.io.protobuf;

public interface X13ResultsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:x13.X13Results)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.x13.X13Preadjustment preadjustment = 1;</code>
   * @return Whether the preadjustment field is set.
   */
  boolean hasPreadjustment();
  /**
   * <code>.x13.X13Preadjustment preadjustment = 1;</code>
   * @return The preadjustment.
   */
  demetra.x13.io.protobuf.X13Preadjustment getPreadjustment();
  /**
   * <code>.x13.X13Preadjustment preadjustment = 1;</code>
   */
  demetra.x13.io.protobuf.X13PreadjustmentOrBuilder getPreadjustmentOrBuilder();

  /**
   * <code>.regarima.RegArimaModel preprocessing = 2;</code>
   * @return Whether the preprocessing field is set.
   */
  boolean hasPreprocessing();
  /**
   * <code>.regarima.RegArimaModel preprocessing = 2;</code>
   * @return The preprocessing.
   */
  demetra.regarima.io.protobuf.RegArimaProtos.RegArimaModel getPreprocessing();
  /**
   * <code>.regarima.RegArimaModel preprocessing = 2;</code>
   */
  demetra.regarima.io.protobuf.RegArimaProtos.RegArimaModelOrBuilder getPreprocessingOrBuilder();

  /**
   * <code>.x13.X11Results decomposition = 3;</code>
   * @return Whether the decomposition field is set.
   */
  boolean hasDecomposition();
  /**
   * <code>.x13.X11Results decomposition = 3;</code>
   * @return The decomposition.
   */
  demetra.x13.io.protobuf.X11Results getDecomposition();
  /**
   * <code>.x13.X11Results decomposition = 3;</code>
   */
  demetra.x13.io.protobuf.X11ResultsOrBuilder getDecompositionOrBuilder();

  /**
   * <code>.x13.X13Finals final = 4;</code>
   * @return Whether the final field is set.
   */
  boolean hasFinal();
  /**
   * <code>.x13.X13Finals final = 4;</code>
   * @return The final.
   */
  demetra.x13.io.protobuf.X13Finals getFinal();
  /**
   * <code>.x13.X13Finals final = 4;</code>
   */
  demetra.x13.io.protobuf.X13FinalsOrBuilder getFinalOrBuilder();

  /**
   * <code>.x13.Diagnostics diagnostics_x13 = 5;</code>
   * @return Whether the diagnosticsX13 field is set.
   */
  boolean hasDiagnosticsX13();
  /**
   * <code>.x13.Diagnostics diagnostics_x13 = 5;</code>
   * @return The diagnosticsX13.
   */
  demetra.x13.io.protobuf.Diagnostics getDiagnosticsX13();
  /**
   * <code>.x13.Diagnostics diagnostics_x13 = 5;</code>
   */
  demetra.x13.io.protobuf.DiagnosticsOrBuilder getDiagnosticsX13OrBuilder();

  /**
   * <code>.sa.Diagnostics diagnostics_sa = 6;</code>
   * @return Whether the diagnosticsSa field is set.
   */
  boolean hasDiagnosticsSa();
  /**
   * <code>.sa.Diagnostics diagnostics_sa = 6;</code>
   * @return The diagnosticsSa.
   */
  demetra.sa.io.protobuf.SaProtos.Diagnostics getDiagnosticsSa();
  /**
   * <code>.sa.Diagnostics diagnostics_sa = 6;</code>
   */
  demetra.sa.io.protobuf.SaProtos.DiagnosticsOrBuilder getDiagnosticsSaOrBuilder();
}
