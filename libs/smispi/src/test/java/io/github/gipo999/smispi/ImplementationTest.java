package io.github.gipo999.smispi;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.gipo999.smispi.interfaces.NamedService001;
import io.github.gipo999.smispi.interfaces.NamedService002;
import io.github.gipo999.smispi.interfaces.NamedService003;
import io.github.gipo999.smispi.interfaces.NamedService004;
import io.github.gipo999.smispi.params.StdCustParams;
import io.github.gipo999.smispi.selectors.CustomSelector;
import io.github.gipo999.smispi.selectors.EmptySelector;
import org.junit.jupiter.api.Test;

class ImplementationTest {

  @Test
  void testStandardOnlyNoCache() {
    var named001 = Implementation.ofNew(NamedService001.class);
    assertTrue(named001.isPresent());
    if (named001.isPresent()) {
      assertTrue(named001.get().getClass().getName().endsWith("Standard"));
      var named001Clone = Implementation.ofNew(NamedService001.class);
      assertTrue(named001Clone.isPresent());
      assertNotSame(named001.get(), named001Clone.get());
    }
  }

  @Test
  void testCustomOnlyNoCache() {
    var named002 = Implementation.ofNew(NamedService002.class);
    assertTrue(named002.isPresent());
    if (named002.isPresent()) {
      assertTrue(named002.get().getClass().getName().endsWith("Custom"));
      var named002Clone = Implementation.ofNew(NamedService002.class);
      assertTrue(named002Clone.isPresent());
      assertNotSame(named002.get(), named002Clone.get());
    }
  }

  @Test
  void testBothOnlyNoCache() {
    var named003 = Implementation.ofNew(NamedService003.class);
    assertTrue(named003.isPresent());
    if (named003.isPresent()) {
      assertTrue(named003.get().getClass().getName().endsWith("Custom"));
      var named003Cone = Implementation.ofNew(NamedService003.class);
      assertTrue(named003Cone.isPresent());
      assertNotSame(named003.get(), named003Cone.get());
    }
  }

  @Test
  void testNoPresenceNoCache() {
    var named004 = Implementation.ofNew(NamedService004.class);
    assertTrue(named004.isEmpty());
  }

  @Test
  void testEmptySelectorNoCache() {
    var named001 = Implementation.ofNew(NamedService001.class, new EmptySelector());
    assertTrue(named001.isEmpty());
  }

  @Test
  void testGlobalEmptySelectorNoCache() {
    Implementation.setGlobalSelector(new EmptySelector());
    var named001 = Implementation.ofNew(NamedService001.class);
    assertTrue(named001.isEmpty());
    Implementation.setGlobalSelector(new DefaultImplementationSelector());
  }

  @Test
  void testCustomSelectorWithParamsNoCache() {
    var named003Std =
        Implementation.ofNew(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertTrue(named003Std.isPresent());
    assertTrue(named003Std.get().getClass().getName().endsWith("Standard"));
    var named003StdClone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertNotSame(named003Std.get(), named003StdClone.get());

    var named003Cus =
        Implementation.ofNew(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertTrue(named003Cus.isPresent());
    assertTrue(named003Cus.get().getClass().getName().endsWith("Custom"));
    var named003CusClone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertNotSame(named003Cus.get(), named003CusClone.get());

    var named003No =
        Implementation.ofNew(NamedService003.class, new CustomSelector(), new StdCustParams("asd"));
    assertTrue(named003No.isEmpty());
  }

  @Test
  void testStandardOnlyWithCache() {
    var named001 = Implementation.of(NamedService001.class);
    assertTrue(named001.isPresent());
    if (named001.isPresent()) {
      assertTrue(named001.get().getClass().getName().endsWith("Standard"));
      var named001Clone = Implementation.of(NamedService001.class);
      assertTrue(named001Clone.isPresent());
      assertSame(named001.get(), named001Clone.get());
    }
  }

  @Test
  void testCustomOnlyWithCache() {
    var named002 = Implementation.of(NamedService002.class);
    assertTrue(named002.isPresent());
    if (named002.isPresent()) {
      assertTrue(named002.get().getClass().getName().endsWith("Custom"));
      var named002Clone = Implementation.of(NamedService002.class);
      assertTrue(named002Clone.isPresent());
      assertSame(named002.get(), named002Clone.get());
    }
  }

  @Test
  void testBothOnlyWithCache() {
    var named003 = Implementation.of(NamedService003.class);
    assertTrue(named003.isPresent());
    if (named003.isPresent()) {
      assertTrue(named003.get().getClass().getName().endsWith("Custom"));
      var named003Clone = Implementation.of(NamedService003.class);
      assertTrue(named003Clone.isPresent());
      assertSame(named003.get(), named003Clone.get());
    }
  }

  @Test
  void testNoPresenceWithCache() {
    var named004 = Implementation.of(NamedService004.class);
    assertTrue(named004.isEmpty());
  }

  @Test
  void testEmptySelectorWithCache() {
    var named001 = Implementation.of(NamedService001.class, new EmptySelector());
    assertTrue(named001.isEmpty());
  }

  @Test
  void testGlobalEmptySelectorWithCache() {
    Implementation.setGlobalSelector(new EmptySelector());
    var named001 = Implementation.of(NamedService001.class);
    assertTrue(named001.isEmpty());
    Implementation.setGlobalSelector(new DefaultImplementationSelector());
  }

  @Test
  void testCustomSelectorWithParamsWithCache() {
    var named003Std =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertTrue(named003Std.isPresent());
    assertTrue(named003Std.get().getClass().getName().endsWith("Standard"));
    var named003StdClone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("s"));
    assertSame(named003Std.get(), named003StdClone.get());

    var named003Cus =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertTrue(named003Cus.isPresent());
    assertTrue(named003Cus.get().getClass().getName().endsWith("Custom"));
    var named003CusClone =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("c"));
    assertSame(named003Cus.get(), named003CusClone.get());

    var named003No =
        Implementation.of(NamedService003.class, new CustomSelector(), new StdCustParams("asd"));
    assertTrue(named003No.isEmpty());
  }
}
