package controllers

import play.api.mvc._
import play.api.libs.iteratee.Enumerator
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.BarcodeFormat
import java.io.ByteArrayOutputStream
import com.google.zxing.client.j2se.MatrixToImageWriter

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def qrcode = Action {
    implicit req =>
      req.getQueryString("url") match {
        case None => Results.BadRequest
        case Some(url) => {
          val bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, 175, 175)
          val outputStream = new ByteArrayOutputStream()
          MatrixToImageWriter.writeToStream(bitMatrix, "png", outputStream)
          Result(header = ResponseHeader(200, Map(CONTENT_TYPE -> "image/png")), body = Enumerator(outputStream.toByteArray))
        }
      }
  }
}