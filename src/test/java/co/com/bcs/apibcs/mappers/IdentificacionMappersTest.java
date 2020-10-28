package co.com.bcs.apibcs.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import co.com.bcs.apibcs.services.dto.*;
import co.com.bcs.backend.services.client.sam.IdentificacionType;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("tomcat")
class IdentificacionMappersTest {

   @Autowired
   public IdentificacionTypeMapper typeMapper;

   @Test
   void testIdentificacion() {
      assertNotNull(typeMapper);
   }

   @Test
   void testAllNull() {

      assertNull(typeMapper.toIdentificacionType(null));

   }

   @Test
   void tipoIdNullIdNotNull() {
      Identificacion identificacion = Identificacion.builder().id("123").build();
      IdentificacionType result = typeMapper.toIdentificacionType(identificacion);
      assertEquals("123", result.getNumIdentificacion());
      assertNull(result.getTpIdentidicacion());
   }

   @Test
   void tipoIdNotNullIdNull() {

      Identificacion identificacion = Identificacion.builder().tipoId("CC").build();
      IdentificacionType result = typeMapper.toIdentificacionType(identificacion);
      assertEquals("CC", result.getTpIdentidicacion());
      assertNull(result.getNumIdentificacion());

   }

   @Test
   void testAllNullOTC() {

      assertNull(typeMapper.toIdentificacionTypeOTC(null));

   }

   @Test
   void tipoIdNullIdNotNullOTC() {

      Identificacion identificacion = Identificacion.builder().id("123").build();
      co.com.bcs.backend.services.client.samotc.IdentificacionType result = typeMapper
            .toIdentificacionTypeOTC(identificacion);
      assertEquals("123", result.getNumIdentificacion());
      assertNull(result.getTpIdentidicacion());
   }

   @Test
   void tipoIdNotNullIdNullOTC() {

      Identificacion identificacion = Identificacion.builder().tipoId("CC").build();
      co.com.bcs.backend.services.client.samotc.IdentificacionType result = typeMapper
            .toIdentificacionTypeOTC(identificacion);
      assertEquals("CC", result.getTpIdentidicacion());
      assertNull(result.getNumIdentificacion());

   }

}