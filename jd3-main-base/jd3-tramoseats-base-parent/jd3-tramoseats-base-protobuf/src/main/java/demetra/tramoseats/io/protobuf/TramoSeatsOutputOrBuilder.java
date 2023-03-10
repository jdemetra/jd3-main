// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: tramoseats.proto

package demetra.tramoseats.io.protobuf;

public interface TramoSeatsOutputOrBuilder extends
    // @@protoc_insertion_point(interface_extends:tramoseats.TramoSeatsOutput)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.tramoseats.TramoSeatsResults result = 1;</code>
   * @return Whether the result field is set.
   */
  boolean hasResult();
  /**
   * <code>.tramoseats.TramoSeatsResults result = 1;</code>
   * @return The result.
   */
  demetra.tramoseats.io.protobuf.TramoSeatsResults getResult();
  /**
   * <code>.tramoseats.TramoSeatsResults result = 1;</code>
   */
  demetra.tramoseats.io.protobuf.TramoSeatsResultsOrBuilder getResultOrBuilder();

  /**
   * <code>.tramoseats.Spec estimation_spec = 2;</code>
   * @return Whether the estimationSpec field is set.
   */
  boolean hasEstimationSpec();
  /**
   * <code>.tramoseats.Spec estimation_spec = 2;</code>
   * @return The estimationSpec.
   */
  demetra.tramoseats.io.protobuf.Spec getEstimationSpec();
  /**
   * <code>.tramoseats.Spec estimation_spec = 2;</code>
   */
  demetra.tramoseats.io.protobuf.SpecOrBuilder getEstimationSpecOrBuilder();

  /**
   * <code>.tramoseats.Spec result_spec = 3;</code>
   * @return Whether the resultSpec field is set.
   */
  boolean hasResultSpec();
  /**
   * <code>.tramoseats.Spec result_spec = 3;</code>
   * @return The resultSpec.
   */
  demetra.tramoseats.io.protobuf.Spec getResultSpec();
  /**
   * <code>.tramoseats.Spec result_spec = 3;</code>
   */
  demetra.tramoseats.io.protobuf.SpecOrBuilder getResultSpecOrBuilder();

  /**
   * <code>.jd3.ProcessingLogs log = 4;</code>
   * @return Whether the log field is set.
   */
  boolean hasLog();
  /**
   * <code>.jd3.ProcessingLogs log = 4;</code>
   * @return The log.
   */
  demetra.toolkit.io.protobuf.ToolkitProtos.ProcessingLogs getLog();
  /**
   * <code>.jd3.ProcessingLogs log = 4;</code>
   */
  demetra.toolkit.io.protobuf.ToolkitProtos.ProcessingLogsOrBuilder getLogOrBuilder();

  /**
   * <code>map&lt;string, .jd3.ProcessingDetail&gt; details = 5;</code>
   */
  int getDetailsCount();
  /**
   * <code>map&lt;string, .jd3.ProcessingDetail&gt; details = 5;</code>
   */
  boolean containsDetails(
      java.lang.String key);
  /**
   * Use {@link #getDetailsMap()} instead.
   */
  @java.lang.Deprecated
  java.util.Map<java.lang.String, demetra.toolkit.io.protobuf.ToolkitProtos.ProcessingDetail>
  getDetails();
  /**
   * <code>map&lt;string, .jd3.ProcessingDetail&gt; details = 5;</code>
   */
  java.util.Map<java.lang.String, demetra.toolkit.io.protobuf.ToolkitProtos.ProcessingDetail>
  getDetailsMap();
  /**
   * <code>map&lt;string, .jd3.ProcessingDetail&gt; details = 5;</code>
   */

  demetra.toolkit.io.protobuf.ToolkitProtos.ProcessingDetail getDetailsOrDefault(
      java.lang.String key,
      demetra.toolkit.io.protobuf.ToolkitProtos.ProcessingDetail defaultValue);
  /**
   * <code>map&lt;string, .jd3.ProcessingDetail&gt; details = 5;</code>
   */

  demetra.toolkit.io.protobuf.ToolkitProtos.ProcessingDetail getDetailsOrThrow(
      java.lang.String key);
}
