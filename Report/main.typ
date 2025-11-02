#import "@preview/unify:0.7.0": *

#import "@preview/cetz:0.3.1" as cetz: *

#import "@preview/cetz-plot:0.1.0"

#let title = "Lab 6 Assignment"
#let authors = (
  (
    name: "Youssef Ahmed Samy",
    id: 9545
  ),
  (
    name: "Taha Mostafa",
    id: 9539
  ),
  (
    name: "Mazen Elkashlan",
    id: 9445
  ),
  (
    name: "Nada Khaled",
    id: 9038
  )
)
#set document(
  title: title,
  author: authors.map(author => author.name),
)

#let organization = "Faculty of Engineering, Alexandria University\n Computer & Communications Program"
#let course-abrev = "CSE226"
#let course-name = "Programming-II"
#let experiment = "SkillForge UML Design"
#set page(
  footer: context [#datetime.today().display()],
  paper: "a4",
)

#align(center, text(20pt)[#smallcaps[
  #organization
]])
#v(1fr)
#align(center, text(17pt)[#smallcaps[
  #course-abrev \ #course-name
]])
#align(center, pad(y: 0.5em, text(26pt)[
  #title
]))

#align(center, text(17pt)[#smallcaps[
  #experiment
]])


#let auths = authors.map(auth => [
  #auth.name \
  #if auth.keys().contains("id") {
    auth.id
  }
])

#let author-columns = 4

#let auths-align = auths.slice(
  0,
  auths.len() - calc.rem(auths.len(), author-columns),
)
#let auths-rest = auths.slice(
  auths.len() - calc.rem(auths.len(), author-columns),
)

#v(1fr)
#grid(
  align: center,
  gutter: 1.5em,
  columns: (1fr,) * author-columns,
  grid.cell(colspan: author-columns, text(14pt, smallcaps(align(center)[*Authors*]))),
  ..auths-align,
  if calc.rem(auths.len(), author-columns) != 0 {
    grid.cell(
      colspan: author-columns,
      grid(
        columns: (1fr,) * auths-rest.len(),
        column-gutter: 3em,
        ..auths-rest
      ),
    )
  }
)
#v(2fr)
#pagebreak()
#counter(page).update(1)
#set page(footer: context [
  #h(1fr)
  #counter(page).display("1")])
#set text(hyphenate: true)
#set par(justify: true)

#set heading(numbering: "1.1.")
*_Please note_*~~~~~all diagrams are in *vector format*, which means you can zoom-in infinitely without pixelation. Please use that feature if a certain diagram is unclear at first.

= Use Case Diagram
== Outline
Lorem ipsum dolor sit amet. Qui quia iusto et eaque galisum nam laboriosam explicabo et voluptatem quia 33 possimus explicabo ut repellat nihil. Vel galisum quam sed dignissimos eligendi et molestias consequatur.
== Diagram
#figure(
  image("../Diagrams/export/1- Use Case.svg", scaling: "smooth", width: 100%),
  caption: [Use Case Diagram],
) #label("diag-usecase")

= Activity Diagram
== Outline
Lorem ipsum dolor sit amet. Qui quia iusto et eaque galisum nam laboriosam explicabo et voluptatem quia 33 possimus explicabo ut repellat nihil. Vel galisum quam sed dignissimos eligendi et molestias consequatur.
== Diagram
#figure(
  image("../Diagrams/export/2- Activity Diagram.svg", scaling: "smooth", width: 100%),
  caption: [Activity Diagram],
) #label("diag-activity")

= Class Diagram
== Outline
Lorem ipsum dolor sit amet. Qui quia iusto et eaque galisum nam laboriosam explicabo et voluptatem quia 33 possimus explicabo ut repellat nihil. Vel galisum quam sed dignissimos eligendi et molestias consequatur.
== Diagram
#figure(
  image("../Diagrams/export/3- Class Diagram.svg", scaling: "smooth", width: 100%),
  caption: [Class Diagram],
) #label("diag-class")

= Sequence Diagram
== Outline
Lorem ipsum dolor sit amet. Qui quia iusto et eaque galisum nam laboriosam explicabo et voluptatem quia 33 possimus explicabo ut repellat nihil. Vel galisum quam sed dignissimos eligendi et molestias consequatur.
== Diagram
#figure(
  image("../Diagrams/export/4- Sequence Diagram.svg", scaling: "smooth", width: 100%),
  caption: [Sequence Diagram],
) #label("diag-sequence")