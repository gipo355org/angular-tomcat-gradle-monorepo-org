package io.github.gipo999.smispi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.gipo999.smispi.filters.CustomFilter;
import io.github.gipo999.smispi.filters.EmptyFilter;
import io.github.gipo999.smispi.interfaces.PrioritizedService001;
import io.github.gipo999.smispi.interfaces.PrioritizedService002;
import io.github.gipo999.smispi.interfaces.PrioritizedService003;
import io.github.gipo999.smispi.interfaces.PrioritizedService004;
import io.github.gipo999.smispi.params.StdCustParams;
import org.junit.jupiter.api.Test;

class ImplementationsTest {
  // TODO sostituire named -> prioritized NOSONAR
  @Test
  void testStandardOnlyNoCache() {
    var named001 = Implementations.ofNew(PrioritizedService001.class);
    assertFalse(named001.isEmpty());
    assertEquals(1, named001.size());
    assertTrue(named001.get(0).getClass().getName().endsWith("Standard"));
    var named001Clone = Implementations.ofNew(PrioritizedService001.class);
    assertFalse(named001Clone.isEmpty());
    assertEquals(1, named001Clone.size());
    assertNotSame(named001.get(0), named001Clone.get(0));
  }

  @Test
  void testCustomOnlyNoCache() {
    var named002 = Implementations.ofNew(PrioritizedService002.class);
    assertFalse(named002.isEmpty());
    assertEquals(1, named002.size());
    assertTrue(named002.get(0).getClass().getName().endsWith("Custom"));
    var named002Clone = Implementations.ofNew(PrioritizedService002.class);
    assertFalse(named002Clone.isEmpty());
    assertEquals(1, named002Clone.size());
    assertNotSame(named002.get(0), named002Clone.get(0));
  }

  @Test
  void testBothOnlyNoCache() {
    var named003 = Implementations.ofNew(PrioritizedService003.class);
    assertFalse(named003.isEmpty());
    assertEquals(2, named003.size());
    assertTrue(named003.get(0).getClass().getName().endsWith("Custom"));
    assertTrue(named003.get(1).getClass().getName().endsWith("Standard"));
    var named003Clone = Implementations.ofNew(PrioritizedService003.class);
    assertFalse(named003Clone.isEmpty());
    assertEquals(2, named003Clone.size());
    assertNotSame(named003.get(0), named003Clone.get(0));
    assertNotSame(named003.get(1), named003Clone.get(1));
  }

  @Test
  void testNoPresenceNoCache() {
    var named004 = Implementations.ofNew(PrioritizedService004.class);
    assertTrue(named004.isEmpty());
  }

  @Test
  void testEmptySelectorNoCache() {
    var named001 = Implementations.ofNew(PrioritizedService001.class, new EmptyFilter());
    assertTrue(named001.isEmpty());
  }

  @Test
  void testCustomSelectorWithParamsNoCache() {
    var named003Std =
        Implementations.ofNew(
            PrioritizedService003.class, new CustomFilter(), new StdCustParams("s"));
    assertEquals(1, named003Std.size());
    assertTrue(named003Std.get(0).getClass().getName().endsWith("Standard"));
    var named003StdClone =
        Implementations.ofNew(
            PrioritizedService003.class, new CustomFilter(), new StdCustParams("s"));
    assertEquals(1, named003StdClone.size());
    assertTrue(named003StdClone.get(0).getClass().getName().endsWith("Standard"));
    assertNotSame(named003Std.get(0), named003StdClone.get(0));

    var named003Cus =
        Implementations.ofNew(
            PrioritizedService003.class, new CustomFilter(), new StdCustParams("c"));
    assertEquals(1, named003Cus.size());
    assertTrue(named003Cus.get(0).getClass().getName().endsWith("Custom"));
    var named003CusClone =
        Implementations.ofNew(
            PrioritizedService003.class, new CustomFilter(), new StdCustParams("c"));
    assertEquals(1, named003CusClone.size());
    assertTrue(named003CusClone.get(0).getClass().getName().endsWith("Custom"));
    assertNotSame(named003Cus.get(0), named003CusClone.get(0));

    var named003No =
        Implementations.ofNew(
            PrioritizedService003.class, new CustomFilter(), new StdCustParams("asd"));
    assertTrue(named003No.isEmpty());
  }

  @Test
  void testStandardOnlyWithCache() {
    var named001 = Implementations.of(PrioritizedService001.class);
    assertFalse(named001.isEmpty());
    assertEquals(1, named001.size());
    assertTrue(named001.get(0).getClass().getName().endsWith("Standard"));
    var named001Clone = Implementations.of(PrioritizedService001.class);
    assertFalse(named001Clone.isEmpty());
    assertEquals(1, named001Clone.size());
    assertSame(named001.get(0), named001Clone.get(0));
  }

  @Test
  void testCustomOnlyWithCache() {
    var named002 = Implementations.of(PrioritizedService002.class);
    assertFalse(named002.isEmpty());
    assertEquals(1, named002.size());
    assertTrue(named002.get(0).getClass().getName().endsWith("Custom"));
    var named002Clone = Implementations.of(PrioritizedService002.class);
    assertFalse(named002Clone.isEmpty());
    assertEquals(1, named002Clone.size());
    assertSame(named002.get(0), named002Clone.get(0));
  }

  @Test
  void testBothOnlyWithCache() {
    var named003 = Implementations.of(PrioritizedService003.class);
    assertFalse(named003.isEmpty());
    assertEquals(2, named003.size());
    assertTrue(named003.get(0).getClass().getName().endsWith("Custom"));
    assertTrue(named003.get(1).getClass().getName().endsWith("Standard"));
    var named003Clone = Implementations.of(PrioritizedService003.class);
    assertFalse(named003Clone.isEmpty());
    assertEquals(2, named003Clone.size());
    assertSame(named003.get(0), named003Clone.get(0));
    assertSame(named003.get(1), named003Clone.get(1));
  }

  @Test
  void testNoPresenceWithCache() {
    var named004 = Implementations.of(PrioritizedService004.class);
    assertTrue(named004.isEmpty());
  }

  @Test
  void testEmptySelectorWithCache() {
    var named001 = Implementations.of(PrioritizedService001.class, new EmptyFilter());
    assertTrue(named001.isEmpty());
  }

  @Test
  void testCustomSelectorWithParamsWithCache() {
    var named003Std =
        Implementations.of(PrioritizedService003.class, new CustomFilter(), new StdCustParams("s"));
    assertEquals(1, named003Std.size());
    assertTrue(named003Std.get(0).getClass().getName().endsWith("Standard"));
    var named003StdClone =
        Implementations.of(PrioritizedService003.class, new CustomFilter(), new StdCustParams("s"));
    assertEquals(1, named003StdClone.size());
    assertTrue(named003StdClone.get(0).getClass().getName().endsWith("Standard"));
    assertSame(named003Std.get(0), named003StdClone.get(0));

    var named003Cus =
        Implementations.of(PrioritizedService003.class, new CustomFilter(), new StdCustParams("c"));
    assertEquals(1, named003Cus.size());
    assertTrue(named003Cus.get(0).getClass().getName().endsWith("Custom"));
    var named003CusClone =
        Implementations.of(PrioritizedService003.class, new CustomFilter(), new StdCustParams("c"));
    assertEquals(1, named003CusClone.size());
    assertTrue(named003CusClone.get(0).getClass().getName().endsWith("Custom"));
    assertSame(named003Cus.get(0), named003CusClone.get(0));

    var named003No =
        Implementations.of(
            PrioritizedService003.class, new CustomFilter(), new StdCustParams("asd"));
    assertTrue(named003No.isEmpty());
  }
}
