INSERT INTO festival_users (id, full_name, email, password_hash, preferred_genres, budget_preference)
VALUES
  (1, 'Aarav Mehta', 'aarav@example.com', 'hash-aarav', 'music,tech,food', '5000'),
  (2, 'Isha Sharma', 'isha@example.com', 'hash-isha', 'dance,art,street-food', '3000')
ON CONFLICT (id) DO NOTHING;

INSERT INTO venues (id, name, address, latitude, longitude, capacity, venue_type)
VALUES
  (1, 'Main Stage', 'North Festival Grounds', 19.0760, 72.8777, 5000, 'stage'),
  (2, 'Food Court', 'Central Festival Grounds', 19.0775, 72.8785, 1500, 'food'),
  (3, 'Indie Arena', 'South Festival Grounds', 19.0752, 72.8794, 2200, 'stage')
ON CONFLICT (id) DO NOTHING;

INSERT INTO events (id, title, description, category, start_time, end_time, price, score, venue_id)
VALUES
  (1, 'Sunset Beats', 'Opening music performance', 'music', '2026-06-27 18:00:00', '2026-06-27 19:30:00', 1200, 82, 1),
  (2, 'Street Food Trail', 'Local food vendor walkthrough', 'food', '2026-06-27 17:00:00', '2026-06-27 18:00:00', 500, 74, 2),
  (3, 'Indie Night', 'Independent artists showcase', 'music', '2026-06-27 20:00:00', '2026-06-27 21:30:00', 1500, 88, 3),
  (4, 'Culture Jam', 'Fusion performance and dance', 'art', '2026-06-27 19:45:00', '2026-06-27 21:00:00', 900, 79, 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO budget_profiles (id, user_id, total_budget, spent_budget, currency)
VALUES
  (1, 1, 5000, 1700, 'INR'),
  (2, 2, 3000, 900, 'INR')
ON CONFLICT (id) DO NOTHING;

INSERT INTO user_preferences (id, user_id, preference_key, preference_value)
VALUES
  (1, 1, 'genre', 'music'),
  (2, 1, 'budget', '5000'),
  (3, 2, 'genre', 'food'),
  (4, 2, 'budget', '3000')
ON CONFLICT (id) DO NOTHING;

INSERT INTO user_event_interactions (id, user_id, event_id, interaction_type, rating, created_at)
VALUES
  (1, 1, 1, 'view', 5, '2026-06-26 12:00:00'),
  (2, 1, 3, 'save', 5, '2026-06-26 12:15:00'),
  (3, 2, 2, 'view', 4, '2026-06-26 13:00:00')
ON CONFLICT (id) DO NOTHING;

INSERT INTO schedules (id, user_id, title, generated_at)
VALUES
  (1, 1, 'Aarav Festival Schedule', '2026-06-27 09:00:00'),
  (2, 2, 'Isha Festival Schedule', '2026-06-27 09:00:00')
ON CONFLICT (id) DO NOTHING;

INSERT INTO schedule_items (id, schedule_id, event_id, planned_start_time, planned_end_time, display_order)
VALUES
  (1, 1, 2, '2026-06-27 17:00:00', '2026-06-27 18:00:00', 1),
  (2, 1, 1, '2026-06-27 18:00:00', '2026-06-27 19:30:00', 2),
  (3, 1, 3, '2026-06-27 20:00:00', '2026-06-27 21:30:00', 3),
  (4, 2, 2, '2026-06-27 17:00:00', '2026-06-27 18:00:00', 1),
  (5, 2, 4, '2026-06-27 19:45:00', '2026-06-27 21:00:00', 2)
ON CONFLICT (id) DO NOTHING;

INSERT INTO crowd_snapshots (id, venue_id, snapshot_time, actual_count, predicted_count, crowd_level, congestion_risk)
VALUES
  (1, 1, '2026-06-27 18:00:00', 1800, 2200, 'high', 0.82),
  (2, 2, '2026-06-27 18:00:00', 600, 750, 'medium', 0.45),
  (3, 3, '2026-06-27 18:00:00', 1300, 1400, 'medium', 0.51)
ON CONFLICT (id) DO NOTHING;

INSERT INTO transactions (id, user_id, amount, category, description, transaction_time)
VALUES
  (1, 1, 1200, 'event', 'Sunset Beats ticket', '2026-06-27 10:00:00'),
  (2, 1, 500, 'food', 'Street food pass', '2026-06-27 10:15:00'),
  (3, 2, 900, 'event', 'Culture Jam ticket', '2026-06-27 10:20:00')
ON CONFLICT (id) DO NOTHING;

INSERT INTO recommendations (id, user_id, event_id, recommendation_type, reason, score, created_at)
VALUES
  (1, 1, 3, 'event', 'matches your interests, fits your budget', 95, '2026-06-27 11:00:00'),
  (2, 1, 1, 'event', 'matches your interests', 90, '2026-06-27 11:00:00'),
  (3, 2, 2, 'event', 'fits your budget', 84, '2026-06-27 11:00:00')
ON CONFLICT (id) DO NOTHING;

INSERT INTO analytics_snapshots (id, snapshot_time, active_users, events_today, locations, alerts, total_revenue)
VALUES
  (1, '2026-06-27 12:00:00', 1248, 329, 58, 7, 621508)
ON CONFLICT (id) DO NOTHING;