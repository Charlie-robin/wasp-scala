package utils

object StringUtils {
  def centre(input: String, totalLength: Int): String = {
    val totalPadding = totalLength - input.length
    val padding = " ".repeat(totalPadding / 2)
    padding + input + padding
  }
}
