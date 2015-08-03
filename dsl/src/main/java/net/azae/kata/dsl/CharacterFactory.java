package net.azae.kata.dsl;

import com.google.common.collect.Iterables;
import net.azae.kata.util.Closure;
import net.azae.kata.util.ClosureUtils;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;

public final class CharacterFactory {

    private CharacterFactory() {
    }

    public static class CharacterBuilder {
        String race;
        Stereotype stereotype;
        final List<Attribute> attributes = newArrayList();
        final List<Skill> skills = newArrayList();

        Character build(final String name) {
            return new Character(name, race, stereotype, attributes, skills);
        }
    }

    public static Closure<CharacterBuilder> race(final String value) {
        return builder -> builder.race = value;
    }

    public static Closure<CharacterBuilder> is(final Stereotype value) {
        return builder -> builder.stereotype = value;
    }

    public static Closure<CharacterBuilder> attributes(final Attribute... attributes) {
        return builder -> Iterables.addAll(builder.attributes, asList(attributes));
    }

    @SafeVarargs
    public static Character character(final String name, final Closure<CharacterBuilder>... actions) {
        return ClosureUtils.processAll(new CharacterBuilder(), asList(actions)).build(name);
    }
}
