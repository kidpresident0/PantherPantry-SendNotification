package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemplateTest {
    @Test
    void getTemplateInvalidID() {
        Template template = Template.getTemplate(666);
        assertEquals(null, template);
    }

    @Test
    void getTemplateValidID() {
        Template template = Template.getTemplate(2);
        assertEquals(2, template.getUserID());
        assertEquals("Winter Treats", template.getTemplateName());
        assertEquals("Winter Treats", template.getSubject());
    }

    @Test
    void getUserID() {
        Template userID = new Template(
                1,
                "Template name #1",
                "Subject #1",
                "TemplateText #1");
        assertEquals(1, userID.getUserID());
    }

    @Test
    void getTemplateName() {
        Template templateName = new Template(
                1,
                "Winter treats",
                "Subject #1",
                "TemplateText #1");
        assertEquals("Winter treats", templateName.getTemplateName());
    }

    @Test
    void getSubject() {
        Template subject = new Template(
                1,
                "Winter treats",
                "Subject of the template",
                "TemplateText #1");
        assertEquals("Subject of the template", subject.getSubject());
    }

    @Test
    void getTemplateText() {
        Template text = new Template(
                1,
                "Winter treats",
                "Subject of the template",
                "TemplateText for the winter treats");
        assertEquals("TemplateText for the winter treats", text.getTemplateText());
    }
}