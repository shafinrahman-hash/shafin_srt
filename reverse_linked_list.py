class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

def reverse_linked_list(head):
    prev = None
    current = head
    while current:
        nxt = current.next    # save next
        current.next = prev   # reverse pointer
        prev = current        # move prev
        current = nxt         # move current
    return prev  # new head


head = Node(1)
head.next = Node(2)
head.next.next = Node(3)

# Reverse it
new_head = reverse_linked_list(head)

# Print reversed list
temp = new_head
while temp:
    print(temp.data, end=" -> ")
    temp = temp.next
print("None")