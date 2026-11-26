package com.example.data

import androidx.annotation.DrawableRes
import com.example.R

enum class CourseTrackId(val label: String) {
    ALL("All Classes"),
    V_VIII("Class V–VIII"),
    IX_X("Class IX–X"),
    XI_XII("Class XI–XII"),
}

data class CourseTrack(
    val id: CourseTrackId,
    val code: String,
    val classRange: String,
    val title: String,
    val subtitle: String,
    val summary: String,
    val keyFocusAreas: List<String>,
    val syllabusHighlights: List<String>,
    val whatsappPrefill: String,
)

data class FeatureItem(
    val id: String,
    val title: String,
    val description: String,
    val iconKey: String,
)

data class WhyChooseItem(
    val number: String,
    val title: String,
    val description: String,
)

data class MentorFocusItem(
    val title: String,
    val detail: String,
)

data class GalleryItem(
    val id: String,
    val title: String,
    val category: String,
    val caption: String,
    @DrawableRes val imageRes: Int,
)

object TuitionRepository {
    const val INSTITUTION_NAME = "ALOR DISHARI ENGLISH TUITION"
    const val TEACHER_NAME = "Bhajahari Gorai"
    const val CLASSES_COVERED = "Classes V–XII"
    const val TAGLINE = "Start with a Dream, Finish with a Future."
    const val PHONE_PRIMARY = "8145614076"
    const val PHONE_SECONDARY = "7908523487"
    const val WHATSAPP_NUMBER_INTL = "918145614076"
    const val GOOGLE_MAPS_URL = "https://maps.app.goo.gl/ySxSMN26ySBZPNnK6"

    const val DEFAULT_WHATSAPP_MESSAGE =
        "Hello Sir, I would like to enquire about admission at Alor Dishari English Tuition (Classes V–XII). Please share batch details and admission process."

    val classOptions: List<String> = listOf(
        "Class V",
        "Class VI",
        "Class VII",
        "Class VIII",
        "Class IX",
        "Class X",
        "Class XI",
        "Class XII",
    )

    val aboutParagraphs: List<String> = listOf(
        "Alor Dishari English Tuition is a dedicated academic learning center focused on guiding students from Class V through Class XII toward strong command over the English language.",
        "Guided by Bhajahari Gorai, every lesson emphasizes clear grammatical understanding, thoughtful literary comprehension, and confident written expression rather than rote memorization.",
        "Whether a student is building early foundational fluency in middle school or preparing methodically for Secondary and Higher Secondary board examinations, each batch follows a calm, disciplined, and supportive roadmap.",
    )

    val courseTracks: List<CourseTrack> = listOf(
        CourseTrack(
            id = CourseTrackId.V_VIII,
            code = "01",
            classRange = "Class V – VIII",
            title = "Foundation English Mastery",
            subtitle = "Middle School Core Grammar & Reading Fluency",
            summary = "Builds an unshakable base in English grammar, sentence construction, vocabulary growth, and clear reading comprehension so students feel confident early.",
            keyFocusAreas = listOf(
                "Core Grammar",
                "Reading Fluency",
                "Guided Writing",
                "Vocabulary",
            ),
            syllabusHighlights = listOf(
                "Parts of speech, tenses, voice, articles, prepositions, and clear sentence structure",
                "Line-by-line textbook understanding with word meanings and contextual usage",
                "Guided paragraph, informal letter, story, and dialogue writing practice",
                "Unseen comprehension drills to develop independent reading confidence",
            ),
            whatsappPrefill = "Hello Sir, I would like to enquire about Class V–VIII English tuition batches at Alor Dishari English Tuition.",
        ),
        CourseTrack(
            id = CourseTrackId.IX_X,
            code = "02",
            classRange = "Class IX – X",
            title = "Secondary Board Preparation",
            subtitle = "Applied Grammar, Writing Formats & Exam Readiness",
            summary = "Prepares Secondary students with thorough textual mastery, transformation of sentences, high-scoring writing formats, and regular test practice.",
            keyFocusAreas = listOf(
                "Board Literature",
                "Applied Grammar",
                "Writing Formats",
                "Practice Tests",
            ),
            syllabusHighlights = listOf(
                "Detailed analysis of prescribed prose, poetry, and supplementary texts",
                "Applied grammar: joining, splitting, narration, voice change, and clause transformation",
                "Structured writing mastery: notice, formal/informal letters, reports, biography, and process writing",
                "Timed unseen passage training and periodic written assessments with individual feedback",
            ),
            whatsappPrefill = "Hello Sir, I would like to enquire about Class IX–X Secondary English tuition batches at Alor Dishari English Tuition.",
        ),
        CourseTrack(
            id = CourseTrackId.XI_XII,
            code = "03",
            classRange = "Class XI – XII",
            title = "Higher Secondary Excellence",
            subtitle = "Critical Literature, Advanced Syntax & Precision Writing",
            summary = "Equips Higher Secondary learners with analytical literature interpretation, refined textual grammar, academic composition, and polished exam presentation.",
            keyFocusAreas = listOf(
                "Critical Literature",
                "Textual Grammar",
                "Academic Writing",
                "Answer Presentation",
            ),
            syllabusHighlights = listOf(
                "In-depth study of prescribed prose, poetry, and drama with thematic clarity",
                "Textual grammar precision, error correction, and advanced sentence transformation",
                "Structured answer framing for long questions, précis, reports, and formal letters",
                "Full-syllabus revision cycles and focused doubt-clearing before board examinations",
            ),
            whatsappPrefill = "Hello Sir, I would like to enquire about Class XI–XII Higher Secondary English tuition batches at Alor Dishari English Tuition.",
        ),
    )

    val specialFeatures: List<FeatureItem> = listOf(
        FeatureItem(
            id = "concept_grammar",
            title = "Concept-First Grammar",
            description = "Grammar rules are explained logically with relatable examples so students understand how sentences work instead of memorizing patterns.",
            iconKey = "grammar",
        ),
        FeatureItem(
            id = "structured_writing",
            title = "Structured Writing Workshops",
            description = "Step-by-step coaching for paragraphs, letters, notices, reports, and essays focusing on clarity, coherence, and accurate vocabulary.",
            iconKey = "writing",
        ),
        FeatureItem(
            id = "textual_clarity",
            title = "Line-by-Line Literature Study",
            description = "Thoughtful reading of prose, poetry, and drama that helps learners interpret themes, character nuances, and textual questions independently.",
            iconKey = "literature",
        ),
        FeatureItem(
            id = "regular_tests",
            title = "Periodic Assessments & Feedback",
            description = "Regular chapter-wise written tests with attentive copy checking to identify common errors early and build steady exam composure.",
            iconKey = "assessment",
        ),
        FeatureItem(
            id = "personal_attention",
            title = "Individual Attention & Doubt Clearing",
            description = "Supportive classroom environment where every learner is encouraged to ask questions and strengthen weak areas at a steady pace.",
            iconKey = "mentor",
        ),
        FeatureItem(
            id = "board_alignment",
            title = "Exam-Oriented Presentation",
            description = "Practical guidance on time management, neat answer structure, and writing concise, relevant responses aligned with school and board expectations.",
            iconKey = "exam",
        ),
    )

    val whyChooseUsItems: List<WhyChooseItem> = listOf(
        WhyChooseItem(
            number = "01",
            title = "Single-Subject Dedicated Focus",
            description = "Dedicated exclusively to English language and literature for Classes V–XII, ensuring deep attention to every aspect of reading, grammar, and writing.",
        ),
        WhyChooseItem(
            number = "02",
            title = "Direct Mentorship by Bhajahari Gorai",
            description = "Every student learns directly under the personal guidance of Bhajahari Gorai with consistent teaching standards across all classes.",
        ),
        WhyChooseItem(
            number = "03",
            title = "Step-by-Step Academic Progression",
            description = "A continuous curriculum bridge from Class V fundamentals up to Class XII Higher Secondary precision, eliminating learning gaps along the way.",
        ),
        WhyChooseItem(
            number = "04",
            title = "Clear Communication with Parents",
            description = "Accessible phone and WhatsApp communication so parents and guardians can easily stay informed about admissions, schedules, and student progress.",
        ),
    )

    val mentorFocusItems: List<MentorFocusItem> = listOf(
        MentorFocusItem(
            title = "Clarity Over Memorization",
            detail = "Helps students grasp the logic behind English grammar and syntax so they can construct original, accurate answers with confidence.",
        ),
        MentorFocusItem(
            title = "Thoughtful Literature Guidance",
            detail = "Breaks down prose, poetry, and drama clearly so every chapter feels approachable, meaningful, and memorable.",
        ),
        MentorFocusItem(
            title = "Polished Writing Craft",
            detail = "Guides students through brainstorming, organizing ideas, and refining expression for letters, essays, reports, and comprehension.",
        ),
        MentorFocusItem(
            title = "Calm Exam Discipline",
            detail = "Instills regular practice habits, neat presentation, and steady preparation tailored to school and board exam standards.",
        ),
    )

    val galleryItems: List<GalleryItem> = listOf(
        GalleryItem(
            id = "sanctuary",
            title = "A Focused Place to Learn",
            category = "Study Environment",
            caption = "A calm, purposeful academic atmosphere designed to inspire curiosity in English literature, grammar, and daily writing practice.",
            imageRes = R.drawable.img_hero_banner,
        ),
        GalleryItem(
            id = "mentorship",
            title = "Guided Personal Mentorship",
            category = "Teaching Approach",
            caption = "Direct, approachable mentorship by Bhajahari Gorai where complex grammar concepts and literary passages are explained with patience.",
            imageRes = R.drawable.img_teacher_mentor,
        ),
        GalleryItem(
            id = "classroom",
            title = "Engaged Classroom Sessions",
            category = "Classes V–XII",
            caption = "Structured batch sessions encouraging attentive reading, interactive doubt clearing, and disciplined written exercises.",
            imageRes = R.drawable.img_gallery_classroom,
        ),
        GalleryItem(
            id = "craft",
            title = "Grammar & Literary Craft",
            category = "Academic Rigor",
            caption = "Carefully organized notes, textual analysis, and writing drills tailored for Foundation, Secondary, and Higher Secondary learners.",
            imageRes = R.drawable.img_gallery_study,
        ),
    )

    fun buildEnquiryWhatsAppMessage(
        studentName: String,
        studentClass: String,
        phone: String,
        notes: String,
    ): String {
        val builder = StringBuilder()
        builder.append("Hello Bhajahari Sir, I would like to submit an admission enquiry for Alor Dishari English Tuition.\n\n")
        builder.append("Student Name: ").append(studentName.trim()).append("\n")
        builder.append("Class: ").append(studentClass).append("\n")
        builder.append("Contact Number: ").append(phone.trim())
        if (notes.isNotBlank()) {
            builder.append("\nMessage: ").append(notes.trim())
        }
        builder.append("\n\nPlease share available batch timings and admission details.")
        return builder.toString()
    }
}
